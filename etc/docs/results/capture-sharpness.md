### Games sorted by capture sharpness (count of different capture moves)

```sql
select maxCaptureSharpness,
       maxCaptureSharpnessPly,
       url || '#' || toString(maxCaptureSharpnessPly)
from game_aggregates
ORDER BY maxCaptureSharpness DESC
limit 25;
```


|    |                     |                        |                                                      | 
|----|---------------------|------------------------|------------------------------------------------------| 
| #  | maxCaptureSharpness | maxCaptureSharpnessPly | "concat(url, '#', toString(maxCaptureSharpnessPly))" | 
| 1  | 38                  | 57                     | https://lichess.org/bSrBufW0#57                      | 
| 2  | 30                  | 407                    | https://lichess.org/E7TmCTkP#407                     | 
| 3  | 29                  | 85                     | https://lichess.org/4pUoCwBI#85                      | 
| 4  | 28                  | 38                     | https://lichess.org/U1TGG7CR#38                      | 
| 5  | 28                  | 60                     | https://lichess.org/J6FdETmC#60                      | 
| 6  | 28                  | 109                    | https://lichess.org/mSDnbAtv#109                     | 
| 7  | 26                  | 70                     | https://lichess.org/3TIXLby4#70                      | 
| 8  | 26                  | 45                     | https://lichess.org/cq40her1#45                      | 
| 9  | 26                  | 43                     | https://lichess.org/AlMfnLWR#43                      | 
| 10 | 25                  | 68                     | https://lichess.org/shIhqTdO#68                      | 
| 11 | 25                  | 78                     | https://lichess.org/LeWyckx3#78                      | 
| 12 | 25                  | 52                     | https://lichess.org/wM9Noq8S#52                      | 
| 13 | 24                  | 50                     | https://lichess.org/lXJnZ2ez#50                      | 
| 14 | 24                  | 125                    | https://lichess.org/OWitoLRd#125                     | 
| 15 | 24                  | 90                     | https://lichess.org/sF6KdWNw#90                      | 
| 16 | 24                  | 68                     | https://lichess.org/v5RyxIIG#68                      | 
| 17 | 24                  | 72                     | https://lichess.org/wpsVvjVV#72                      | 
| 18 | 24                  | 24                     | https://lichess.org/2qebIY0n#24                      | 
| 19 | 24                  | 64                     | https://lichess.org/9MiHttci#64                      | 
| 20 | 24                  | 82                     | https://lichess.org/CCkFC0l5#82                      | 
| 21 | 24                  | 54                     | https://lichess.org/QBY7YR7p#54                      | 
| 22 | 23                  | 41                     | https://lichess.org/AIq66HYB#41                      | 
| 23 | 23                  | 38                     | https://lichess.org/htevRJe5#38                      | 
| 24 | 23                  | 72                     | https://lichess.org/GKijofNR#72                      | 
| 25 | 23                  | 76                     | https://lichess.org/4hx1GwTA#76                      | 
