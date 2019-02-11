create table game_aggregates
(
	id bigserial not null
		constraint game_aggregates_pk
			primary key,
	url text not null
	  constraint url_unique unique,
	aggs jsonb not null DEFAULT '{}'::jsonb
);