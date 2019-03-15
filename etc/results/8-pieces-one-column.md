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
ORDER BY allPiecesInColMaterialValue DESC ,avgRating DESC
limit 25;
```

| #  | directLinkMove                  | blackElo | whiteElo | allPiecesInColumnPly | allPiecesInColumnCol | allPiecesInColMaterialValue | 
|----|---------------------------------|----------|----------|----------------------|----------------------|-----------------------------| 
| 1  | https://lichess.org/JLF5LkY4#61 | 2071     | 2110     | 61                   | 6                    | 5250                        | 
| 2  | https://lichess.org/zryQUEDZ#68 | 2087     | 2413     | 68                   | 6                    | 5200                        | 
| 3  | https://lichess.org/Lfe6oWqE#62 | 2168     | 2182     | 62                   | 5                    | 5200                        | 
| 4  | https://lichess.org/zmRT0Kyh#68 | 2050     | 2017     | 68                   | 6                    | 5200                        | 
| 5  | https://lichess.org/Wj1hmjzE#70 | 2073     | 1961     | 70                   | 6                    | 5200                        | 
| 6  | https://lichess.org/8aFhdNPU#72 | 2207     | 2175     | 72                   | 6                    | 5050                        | 
| 7  | https://lichess.org/0stP0VkO#61 | 2412     | 2781     | 61                   | 6                    | 5025                        | 
| 8  | https://lichess.org/yNDZpaQq#68 | 2317     | 2278     | 68                   | 6                    | 5025                        | 
| 9  | https://lichess.org/uROfAU49#48 | 2335     | 2239     | 48                   | 6                    | 5025                        | 
| 10 | https://lichess.org/kE6tU2gw#58 | 2247     | 2301     | 58                   | 6                    | 5025                        | 
| 11 | https://lichess.org/o2dx8imm#83 | 2096     | 2204     | 83                   | 6                    | 5025                        | 
| 12 | https://lichess.org/z7RjS9mn#70 | 2223     | 2075     | 70                   | 6                    | 5025                        | 
| 13 | https://lichess.org/IhkRI2bw#44 | 2180     | 2097     | 44                   | 6                    | 5025                        | 
| 14 | https://lichess.org/idMPcSM5#55 | 2000     | 2246     | 55                   | 6                    | 5025                        | 
| 15 | https://lichess.org/lNn9rhAv#59 | 1997     | 2229     | 59                   | 6                    | 5025                        | 
| 16 | https://lichess.org/auvKYBbE#58 | 2097     | 2129     | 58                   | 6                    | 5025                        | 
| 17 | https://lichess.org/nQMuWVI9#74 | 2213     | 2007     | 74                   | 6                    | 5025                        | 
| 18 | https://lichess.org/TELsFPKF#46 | 2176     | 2041     | 46                   | 6                    | 5025                        | 
| 19 | https://lichess.org/rwJCC1dv#61 | 2100     | 2077     | 61                   | 6                    | 5025                        | 
| 20 | https://lichess.org/Gf61ftJc#73 | 2139     | 2018     | 73                   | 6                    | 5025                        | 
| 21 | https://lichess.org/68v9s4RO#41 | 1717     | 2407     | 41                   | 6                    | 5025                        | 
| 22 | https://lichess.org/3pnsd7Ps#58 | 2056     | 2056     | 58                   | 6                    | 5025                        | 
| 23 | https://lichess.org/eZJQ07jG#64 | 2034     | 2067     | 64                   | 6                    | 5025                        | 
| 24 | https://lichess.org/ZJujiUhl#70 | 2037     | 2055     | 70                   | 6                    | 5025                        | 
| 25 | https://lichess.org/1MrkzvEh#62 | 1975     | 2098     | 62                   | 6                    | 5025                        | 
                      | 


### Per column statistic (0=a,7=h)
```sql
select
       allPiecesInColumnCol,
       round(avg(allPiecesInColMaterialValue)) as avgMaterial,
       min(allPiecesInColMaterialValue) as minMaterial,
       max(allPiecesInColMaterialValue) as maxMaterial
from game_aggregates
WHERE
      allPiecesInColumnPly != 0
GROUP BY allPiecesInColumnCol;
```

| # | allPiecesInColumnCol | avgMaterial | minMaterial | maxMaterial | 
|---|----------------------|-------------|-------------|-------------| 
| 1 | 0                    | 3023        | 2000        | 4800        | 
| 2 | 1                    | 3350        | 1850        | 5025        | 
| 3 | 2                    | 2437        | 1450        | 5200        | 
| 4 | 3                    | 3237        | 1700        | 5200        | 
| 5 | 4                    | 3445        | 1675        | 5200        | 
| 6 | 5                    | 2669        | 1450        | 5200        | 
| 7 | 6                    | 3619        | 1850        | 5425        | 
| 8 | 7                    | 3505        | 2025        | 5225        | 
