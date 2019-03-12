```sql
select (url || '#' || toString(maxForkMaterialAmountPly)) as directLinkMove,
                maxForkMaterialAmount,
                blackElo,
                whiteElo
from game_aggregates
WHERE blackElo > 2000 and whiteElo > 2000
order by maxForkMaterialAmount DESC
limit 100;
```


| url                             |  material | blackElo |  whiteElo | 
|---------------------------------|-----------|----------|-----------| 
| https://lichess.org/4tuJdXPU#56 | 3425      | 2209     | 2189      | 
| https://lichess.org/AqqvcX2g#54 | 3325      | 2415     | 2292      | 
| https://lichess.org/VfDdWAYH#44 | 3325      | 2235     | 2241      | 
| https://lichess.org/5Shir2ns#40 | 3325      | 2165     | 2090      | 
| https://lichess.org/avLBwdV1#44 | 3325      | 2066     | 2014      | 
| https://lichess.org/U8vYnjSe#50 | 3300      | 2413     | 2357      | 
| https://lichess.org/BCOMSkxt#54 | 3300      | 2073     | 2146      | 
| https://lichess.org/y6AGU2TW#75 | 3300      | 2094     | 2009      | 
| https://lichess.org/NP8iDi8a#57 | 3225      | 2419     | 2394      | 
| https://lichess.org/7eUrRwjj#41 | 3225      | 2310     | 2309      | 
| https://lichess.org/v39YoW1l#37 | 3225      | 2298     | 2224      | 
| https://lichess.org/90psAkkG#61 | 3225      | 2263     | 2251      | 
| https://lichess.org/LIhm4fGM#42 | 3225      | 2190     | 2314      | 
| https://lichess.org/IOlgeGaQ#50 | 3225      | 2244     | 2224      | 
| https://lichess.org/hrpD1vqS#48 | 3225      | 2193     | 2267      | 
| https://lichess.org/uxuL2gTm#48 | 3225      | 2220     | 2236      | 
| https://lichess.org/IaMvVNBa#52 | 3225      | 2168     | 2222      | 
| https://lichess.org/Bn9bOElN#61 | 3225      | 2261     | 2113      | 
| https://lichess.org/zPEJRMUf#70 | 3225      | 2013     | 2299      | 
| https://lichess.org/37sKk6Tk#53 | 3225      | 2228     | 2059      | 
