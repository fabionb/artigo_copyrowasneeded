CREATE INDEX test2entity_ix01 on test2entity(test1entity_id);
CREATE INDEX test3entity_ix01 on test3entity(test1entity_id);
CREATE INDEX test4entity_ix01 on test4entity(test3entity_id);
CREATE INDEX test5entity_ix01 on test5entity(test2entity_id);