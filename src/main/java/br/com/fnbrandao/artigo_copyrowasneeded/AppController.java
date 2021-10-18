package br.com.fnbrandao.artigo_copyrowasneeded;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	private static final int SIZE = 25;

	@Autowired
	private EntityManager entityManager;

	@GetMapping("/create")
	@Transactional
	public Long create() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		Test1Entity t1 = new Test1Entity();
		setColumns(t1);

		for (int i = 0; i < SIZE; i++) {
			Test2Entity t2 = new Test2Entity();
			setColumns(t2);
			t2.setTest1Entity(t1);

			for (int j = 0; j < SIZE; j++) {
				Test5Entity t5 = new Test5Entity();
				setColumns(t5);
				t5.setTest2Entity(t2);
				t2.getTest5Entities().add(t5);
			}

			t1.getTest2Entities().add(t2);

			Test3Entity t3 = new Test3Entity();
			setColumns(t3);
			t3.setTest1Entity(t1);

			for (int j = 0; j < SIZE; j++) {
				Test4Entity t4 = new Test4Entity();
				setColumns(t4);
				t4.setTest3Entity(t3);
				t3.getTest4Entities().add(t4);
			}

			t1.getTest3Entities().add(t3);
		}

		entityManager.persist(t1);
		return t1.getId();
	}

	private void setColumns(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		Class<? extends Object> clazz = o.getClass();
		try {
			for (int i = 1; i <= 100; i++) {
				clazz.getDeclaredMethod("setColumn" + i, String.class).invoke(o, "Column Value " + i);
			}
		} catch (NoSuchMethodException e) {
			// Ignore
		}
	}

	@GetMapping("/test/{id}")
	@Transactional
	public Test1Entity test(@PathVariable(name = "id") long id) {
		logger.info("Entering test method " + id);

		Test1Entity t1 = entityManager.createQuery(//
				"SELECT t1 FROM Test1Entity t1 " //
				+ "JOIN FETCH t1.test2Entities t2 " //
				+ "JOIN FETCH t2.test5Entities t5 " //
				+ "JOIN FETCH t1.test3Entities t3 " //
				+ "JOIN FETCH t3.test4Entities t4 " //
				+ "WHERE t1.id = :id", Test1Entity.class) //
				.setParameter("id", id) //
				.getSingleResult();

		logger.info("Exiting test method " + id);

		return t1;
	}

	@GetMapping("/testCorrection/{id}")
	@Transactional
	public Test1Entity testCorrection(@PathVariable(name = "id") long id) {
		logger.info("Entering testCorrection method " + id);

		Test1Entity t1 = entityManager.createQuery(//
				"SELECT t1 FROM Test1Entity t1 " //
				+ "WHERE t1.id = :id", Test1Entity.class) //
				.setParameter("id", id) //
				.getSingleResult();

		t1.setTest2Entities(new HashSet<>(entityManager.createQuery(//
				"SELECT t2 FROM Test2Entity t2 " //
				+ "JOIN FETCH t2.test5Entities t5 " //
				+ "WHERE t2.test1Entity.id = :id", Test2Entity.class) //
				.setParameter("id", id) //
				.getResultList()));

		t1.setTest3Entities(new HashSet<>(entityManager.createQuery(//
				"SELECT t3 FROM Test3Entity t3 " //
				+ "JOIN FETCH t3.test4Entities t4 " //
				+ "WHERE t3.test1Entity.id = :id", Test3Entity.class) //
				.setParameter("id", id) //
				.getResultList()));


		logger.info("Exiting testCorrection method " + id);

		return t1;
	}

}
