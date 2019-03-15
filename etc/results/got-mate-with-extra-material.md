### Got mate with piece advantage. Results sorted by lose side material advantage

```sql
select url,materialFromMatedSideView,gameType, blackElo,whiteElo
from game_aggregates
where avgRating > 1800
order by materialFromMatedSideView desc
LIMIT 25;
```

| #  | url                          | materialFromMatedSideView | gameType | blackElo | whiteElo | 
|----|------------------------------|---------------------------|----------|----------|----------| 
| 1  | https://lichess.org/yWzjvFI2 | 4400                      | 1        | 1945     | 2068     | 
| 2  | https://lichess.org/ReyHEI41 | 4150                      | 2        | 2198     | 2041     | 
| 3  | https://lichess.org/0bamDQAZ | 3225                      | 2        | 1816     | 1815     | 
| 4  | https://lichess.org/y7iXw85k | 3050                      | 1        | 2213     | 1917     | 
| 5  | https://lichess.org/moCUump9 | 3025                      | 1        | 1756     | 1903     | 
| 6  | https://lichess.org/qHi8janX | 2925                      | 2        | 2152     | 1824     | 
| 7  | https://lichess.org/yROSDQN8 | 2700                      | 2        | 1920     | 1896     | 
| 8  | https://lichess.org/blju5gVm | 2700                      | 1        | 2067     | 2283     | 
| 9  | https://lichess.org/meZZEpPU | 2650                      | 1        | 2022     | 1691     | 
| 10 | https://lichess.org/jjginMk7 | 2600                      | 2        | 1971     | 1828     | 
| 11 | https://lichess.org/wI8HzLfI | 2600                      | 1        | 1884     | 1862     | 
| 12 | https://lichess.org/XnjqsSIg | 2600                      | 1        | 2382     | 2590     | 
| 13 | https://lichess.org/CeScrZMH | 2575                      | 2        | 1957     | 1946     | 
| 14 | https://lichess.org/NC5LAbaA | 2575                      | 2        | 2156     | 2164     | 
| 15 | https://lichess.org/8ktW6g1h | 2550                      | 3        | 1996     | 1793     | 
| 16 | https://lichess.org/eAsOdiAb | 2525                      | 2        | 2064     | 2035     | 
| 17 | https://lichess.org/uH112q1e | 2525                      | 1        | 1778     | 1825     | 
| 18 | https://lichess.org/58XqsRaR | 2525                      | 1        | 1910     | 1891     | 
| 19 | https://lichess.org/N9i902M8 | 2525                      | 3        | 1852     | 1935     | 
| 20 | https://lichess.org/GDub4tLe | 2525                      | 1        | 1857     | 1883     | 
| 21 | https://lichess.org/45n9imXM | 2525                      | 3        | 2051     | 2039     | 
| 22 | https://lichess.org/INEsmHiv | 2525                      | 4        | 1926     | 1891     | 
| 23 | https://lichess.org/cSyDspF4 | 2500                      | 1        | 1858     | 1796     | 
| 24 | https://lichess.org/ftghVcJa | 2500                      | 1        | 1920     | 1718     | 
| 25 | https://lichess.org/5H5bwDnl | 2500                      | 1        | 1879     | 1849     | 

