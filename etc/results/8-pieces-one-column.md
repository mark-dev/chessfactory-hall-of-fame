### High rated games with '8 pieces in column' sorted by pieces material amount

```sql
select url || '#' || toString(allPiecesInColumnPly) as directLinkMove,
       blackElo,
       whiteElo,
       allPiecesInColumnPly,
       allPiecesInColumnCol,
       allPiecesInColMaterialValue
from game_aggregates
WHERE
      avgRating > 2000 and
      allPiecesInColumnPly != 0
ORDER BY allPiecesInColMaterialValue ASC ,avgRating DESC
limit 25;
```


|    |                                 |          |          |                      |                      |                             | 
|----|---------------------------------|----------|----------|----------------------|----------------------|-----------------------------| 
| # | directLinkMove                  | blackElo | whiteElo | allPiecesInColumnPly | allPiecesInColumnCol | allPiecesInColMaterialValue | 
| 1  | https://lichess.org/xUppz0KK#28 | 2543     | 1823     | 28                   | 2                    | 1650                        | 
| 2  | https://lichess.org/X1KMEFPp#16 | 2140     | 2203     | 16                   | 2                    | 1650                        | 
| 3  | https://lichess.org/OSmxH35X#44 | 2151     | 2168     | 44                   | 5                    | 1650                        | 
| 4  | https://lichess.org/k3ruskPU#16 | 2437     | 1810     | 16                   | 2                    | 1650                        | 
| 5  | https://lichess.org/fPWChGit#16 | 2054     | 2089     | 16                   | 2                    | 1650                        | 
| 6  | https://lichess.org/nTIFEMiw#41 | 1984     | 2089     | 41                   | 5                    | 1650                        | 
| 7  | https://lichess.org/T0wUYHme#14 | 2483     | 2556     | 14                   | 2                    | 1675                        | 
| 8  | https://lichess.org/sLdephol#25 | 2142     | 2514     | 25                   | 2                    | 1675                        | 
| 9  | https://lichess.org/QHr8Jj7K#24 | 2297     | 2264     | 24                   | 2                    | 1675                        | 
| 10 | https://lichess.org/T5TragJj#18 | 2221     | 2327     | 18                   | 2                    | 1675                        | 
| 11 | https://lichess.org/aJZRFe0b#21 | 2148     | 2344     | 21                   | 2                    | 1675                        | 
| 12 | https://lichess.org/fMifhWyL#17 | 2188     | 2279     | 17                   | 2                    | 1675                        | 
| 13 | https://lichess.org/kxIpBfUX#18 | 2173     | 2209     | 18                   | 2                    | 1675                        | 
| 14 | https://lichess.org/nDPPrlim#14 | 2105     | 2229     | 14                   | 2                    | 1675                        | 
| 15 | https://lichess.org/F8ofGem4#16 | 2107     | 2187     | 16                   | 2                    | 1675                        | 
| 16 | https://lichess.org/NVhets2p#17 | 2125     | 2114     | 17                   | 2                    | 1675                        | 
| 17 | https://lichess.org/6w4Xh1ZA#45 | 2153     | 2079     | 45                   | 2                    | 1675                        | 
| 18 | https://lichess.org/GjEMdW3g#17 | 2209     | 2003     | 17                   | 2                    | 1675                        | 
| 19 | https://lichess.org/shXQMSuH#21 | 2170     | 2029     | 21                   | 2                    | 1675                        | 
| 20 | https://lichess.org/HrjYa2Pm#21 | 2074     | 2109     | 21                   | 2                    | 1675                        | 
| 21 | https://lichess.org/oYFZdtmo#16 | 2161     | 2021     | 16                   | 2                    | 1675                        | 
| 22 | https://lichess.org/GLevmvD1#16 | 2069     | 2110     | 16                   | 2                    | 1675                        | 
| 23 | https://lichess.org/PzKuameP#17 | 2073     | 2086     | 17                   | 2                    | 1675                        | 
| 24 | https://lichess.org/HqbzHqbb#18 | 1984     | 2156     | 18                   | 2                    | 1675                        | 
| 25 | https://lichess.org/4H2Ygvvq#17 | 2091     | 2044     | 17                   | 2                    | 1675                        | 


### Per column statistic (0=a,7=h)
```sql
select
       allPiecesInColumnCol,
       avg(allPiecesInColMaterialValue),
       min(allPiecesInColMaterialValue),
       max(allPiecesInColMaterialValue)
from game_aggregates
WHERE
      allPiecesInColumnPly != 0
GROUP BY allPiecesInColumnCol;
```

|   |                      |                                  |                                  |                                  | 
|---|----------------------|----------------------------------|----------------------------------|----------------------------------| 
| # | allPiecesInColumnCol | avg(allPiecesInColMaterialValue) | min(allPiecesInColMaterialValue) | max(allPiecesInColMaterialValue) | 
| 1 | 0                    | 3022.7669710202463               | 2000                             | 4800                             | 
| 2 | 1                    | 3350.2613636363635               | 1850                             | 5025                             | 
| 3 | 2                    | 2436.7429993777223               | 1450                             | 5200                             | 
| 4 | 3                    | 3237.294551232058                | 1700                             | 5200                             | 
| 5 | 4                    | 3445.354748397554                | 1675                             | 5200                             | 
| 6 | 5                    | 2668.6636928118073               | 1450                             | 5200                             | 
| 7 | 6                    | 3618.841545313163                | 1850                             | 5425                             | 
| 8 | 7                    | 3504.546920348275                | 2025                             | 5225                             | 
