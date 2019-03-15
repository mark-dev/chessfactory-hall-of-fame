### Mate by en passant (including discovered mates)
```sql
select url, blackElo,whiteElo
from game_aggregates
where epMate = 1
  and avgRating > 2000
order by materialFromMatedSideView desc
limit 25;
```

| #  | url                          | blackElo | whiteElo | 
|----|------------------------------|----------|----------| 
| 1  | https://lichess.org/hND1G90l | 2195     | 2154     | 
| 2  | https://lichess.org/xsy1SRcK | 2162     | 2433     | 
| 3  | https://lichess.org/Vv4WkDc1 | 2056     | 2113     | 
| 4  | https://lichess.org/3VkpZJ0j | 2123     | 2071     | 
| 5  | https://lichess.org/cxubq0xe | 2200     | 1983     | 
| 6  | https://lichess.org/7T8RwDmi | 2278     | 2165     | 
| 7  | https://lichess.org/mYgdlKE4 | 2189     | 2184     | 
| 8  | https://lichess.org/v3Ht7WNr | 2159     | 2164     | 
| 9  | https://lichess.org/Ar2RIkEL | 2153     | 2271     | 
| 10 | https://lichess.org/5qhBCGVN | 2004     | 2113     | 
| 11 | https://lichess.org/LU0FPwy5 | 2103     | 2008     | 
| 12 | https://lichess.org/ohZA8iBH | 1984     | 2026     | 
| 13 | https://lichess.org/rUxtooH2 | 1992     | 2056     | 
| 14 | https://lichess.org/mg1u1F68 | 2331     | 1892     | 
| 15 | https://lichess.org/AbtuOd5H | 2048     | 1988     | 
| 16 | https://lichess.org/KglkXSNS | 2108     | 1965     | 
| 17 | https://lichess.org/YSJFuUU0 | 2268     | 1914     | 
| 18 | https://lichess.org/rFC8XfTu | 2212     | 2209     | 
| 19 | https://lichess.org/k0tg529l | 2162     | 2194     | 
| 20 | https://lichess.org/zasmHSDY | 2133     | 2055     | 
| 21 | https://lichess.org/cmCmtzka | 2008     | 2039     | 
| 22 | https://lichess.org/u5P9BO4U | 2132     | 2060     | 
| 23 | https://lichess.org/lcTUf1eR | 2023     | 2036     | 
| 24 | https://lichess.org/krjomsQy | 2102     | 2119     | 
| 25 | https://lichess.org/igDUlDO1 | 2083     | 2037     | 
