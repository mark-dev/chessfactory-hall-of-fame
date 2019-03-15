```sql
select url,knightLongestPath,longestKnightInitialPosition, knightLongestPathDetails
from game_aggregates
order by knightLongestPath DESC
limit 25;
```

| #  | url                          | knightLongestPath | longestKnightInitialPosition | knightLongestPathDetails                                                              | 
|----|------------------------------|-------------------|------------------------------|---------------------------------------------------------------------------------------| 
| 1  | https://lichess.org/oB09yPrY | 28                | g1                           | {g1,e2,c3,b5,c7,e8,g7,f5,e7,d5,f6,g4,h6,f7,d8,e6,d4,c6,e5,d3,b4,c2,e1,g2,h4,g6,f8,d7} | 
| 2  | https://lichess.org/7C94IJ3J | 28                | b8                           | {b8,d7,f8,e6,d8,b7,a5,b3,d2,f3,h4,f5,e7,c6,b4,c2,a3,b5,c3,a4,b6,c4,b2,d3,e1,g2,e3,d5} | 
| 3  | https://lichess.org/t8zMbm7m | 28                | g8                           | {g8,f6,d7,c5,e4,d6,f5,d4,c2,b4,d3,e5,c4,b6,a4,c3,e2,f4,g2,e1,f3,g5,h3,f2,g4,h2,f1,g3} | 
| 4  | https://lichess.org/jOj7e9Xx | 28                | b1                           | {b1,d2,e4,g5,f3,d4,f5,g7,e8,f6,h7,f8,d7,b8,a6,b4,c2,a3,b5,d6,f7,h6,g8,e7,d5,c3,e2,g3} | 
| 5  | https://lichess.org/mVR1JW83 | 28                | b1                           | {b1,c3,a4,c5,b3,c1,d3,f2,d1,e3,d5,e7,g6,h4,f3,e1,c2,a3,b5,c7,e8,g7,h5,f6,d7,b6,c4,a5} | 
| 6  | https://lichess.org/waUW2Zpd | 28                | h2                           | {h2,h8,f7,d6,c8,b6,a8,c7,b5,d4,e6,d8,c6,e7,g6,e5,d7,b8,a6,b4,a2,c1,d3,b2,c4,e3,g4,h6} | 
| 7  | https://lichess.org/eoS6vnW0 | 27                | g1                           | {g1,e2,c1,d3,b4,d5,f6,e4,c5,a6,c7,b5,a7,c8,e7,g8,h6,f7,g5,h7,f8,g6,e5,f3,h2,g4,f2}    | 
| 8  | https://lichess.org/joXALfTz | 27                | b8                           | {b8,c6,e7,g6,h4,f5,g3,h5,f6,d7,b6,a4,b2,d3,f4,e2,c3,b1,a3,c2,d4,f3,g1,h3,g5,f7,e5}    | 
| 9  | https://lichess.org/9jDBqx7q | 27                | g8                           | {g8,h6,g4,e5,f3,d2,c4,b2,d3,f4,h3,f2,e4,c3,a2,b4,c6,d4,c2,e3,f5,d6,b7,c5,b3,c1,e2}    | 
| 10 | https://lichess.org/VH8Tsb1E | 27                | g1                           | {g1,f3,d2,b3,c1,d3,b2,c4,a5,c6,d8,b7,d6,e8,g7,e6,f8,g6,e5,g4,f6,h5,g3,e2,f4,g2,e3}    | 
| 11 | https://lichess.org/tTpEHOK1 | 27                | b1                           | {b1,c3,d5,c7,a6,b4,c6,a7,c8,b6,a4,b2,d1,e3,c2,d4,e6,f8,g6,e5,d3,f2,h1,g3,f5,h6,g4}    | 
| 12 | https://lichess.org/EJ7DNRvw | 27                | g8                           | {g8,e7,f5,h6,f7,e5,g4,f6,h5,f4,e2,g1,h3,f2,e4,g3,f1,e3,d5,c7,b5,a7,c6,a5,b3,c5,b7}    | 
| 13 | https://lichess.org/BHX1XgvI | 27                | b1                           | {b1,c3,b5,d4,c2,a3,c4,d2,f1,g3,h5,f6,d7,c5,d3,b4,a6,c7,a8,b6,c8,d6,e8,g7,e6,d8,c6}    | 
| 14 | https://lichess.org/nYFvpzVB | 27                | g1                           | {g1,f3,d4,e2,f4,d5,c7,e6,g5,h3,f2,e4,d6,c8,b6,a4,b2,d1,e3,f5,e7,c6,b8,a6,c5,b7,a5}    | 
| 15 | https://lichess.org/yN53bHNu | 27                | b1                           | {b1,c3,b5,c7,a8,b6,d7,c5,b3,a5,c6,b4,d3,b2,d1,f2,g4,f6,h7,f8,g6,h4,f5,d6,f7,d8,e6}    | 
| 16 | https://lichess.org/PygayV3L | 27                | g8                           | {g8,f6,d7,b6,c4,e3,d1,c3,e4,d2,b3,a5,b7,d8,f7,h6,g4,f2,h3,f4,g2,h4,f5,d4,c2,b4,d5}    | 
| 17 | https://lichess.org/nb19cQpe | 27                | g8                           | {g8,e7,g6,f4,d5,e3,c2,d4,f3,g1,h3,g5,e6,d8,b7,c5,d3,b2,d1,f2,g4,f6,d7,b6,a8,c7,b5}    | 
| 18 | https://lichess.org/5IcY5jtm | 27                | b1                           | {b1,c3,a4,b6,c8,a7,b5,c7,e8,d6,b7,d8,e6,d4,e2,g3,e4,c5,d7,f8,g6,h4,f3,e1,d3,b4,c6}    | 
| 19 | https://lichess.org/CrqaUxSa | 26                | g1                           | {g1,f3,g5,f7,d6,b5,c3,e2,f4,e6,c5,e4,f2,g4,e3,c2,d4,f5,e7,d5,c7,a8,b6,d7,b8,a6}       | 
| 20 | https://lichess.org/kO3DmMbH | 26                | g1                           | {g1,h3,f2,g4,e5,c6,e7,d5,c7,e6,g7,h5,g3,e2,c1,b3,a5,b7,d8,f7,h6,f5,e3,c2,e1,f3}       | 
| 21 | https://lichess.org/PhqvvwBm | 26                | g8                           | {g8,e7,c6,d8,b7,d6,c8,b6,a4,b2,d3,c1,a2,c3,d1,e3,c2,d4,f3,g5,e4,f2,g4,e5,c4,d2}       | 
| 22 | https://lichess.org/gUjKNux7 | 26                | b8                           | {b8,d7,e5,f7,d6,e4,c3,d1,e3,c2,e1,d3,f4,d5,e7,c6,a5,b3,c1,e2,g1,f3,h4,f5,d4,b5}       | 
| 23 | https://lichess.org/PbfnKqfh | 26                | g1                           | {g1,f3,e1,d3,c5,b3,a5,c6,b8,a6,b4,d5,e7,g8,h6,g4,f2,d1,b2,c4,d6,f7,g5,h7,f8,d7}       | 
| 24 | https://lichess.org/Nfzr7hdV | 26                | b8                           | {b8,c6,e7,f5,h4,g6,f4,d3,b2,c4,a5,b7,c5,a4,c3,a2,c1,e2,d4,f3,g1,h3,f2,g4,f6,e8}       | 
| 25 | https://lichess.org/44ZXgUqQ | 26                | b8                           | {b8,d7,f6,h5,g7,f5,d4,c2,e1,g2,f4,d3,e5,f7,d6,c4,d2,f1,h2,g4,f2,d1,b2,a4,c5,a6}       | 
