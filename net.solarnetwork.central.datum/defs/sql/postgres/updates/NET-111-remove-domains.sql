\echo `date` Starting NET-111 migration

\i NET-111-remove-domains-start.sql
\i NET-111-remove-domains-start-extra.sql
\i NET-111-remove-domains-core.sql
\i NET-111-remove-domains-generic-datum.sql
\i NET-111-remove-domains-generic-datum-agg-functions.sql
\i NET-111-remove-domains-generic-loc-datum.sql
\i NET-111-remove-domains-generic-loc-datum-agg-functions.sql
\i NET-111-remove-domains-generic-datum-x-functions.sql
\i NET-111-remove-domains-generic-loc-datum-x-functions.sql
\i NET-111-remove-domains-users.sql
\i NET-111-remove-domains-end-extra.sql
\i NET-111-remove-domains-drop.sql

\echo `date` Finished NET-111 migration
