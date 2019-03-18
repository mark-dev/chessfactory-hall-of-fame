### Developer info

This code read *.pgn.bz2 files from lichess database, extract pgn games from it, and pass this games though processing pipeline.
configuration avaliable here: /src/main/resources/application.yaml

Some aggregated values calculated for each game, and store it into clickhouse database.

[handlers](/src/main/java/ru/chessfactory/hof/core/calc/handlers)
 [sql schema](/etc/sql)
### TODO: write description..