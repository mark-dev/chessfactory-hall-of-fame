## Chessfactory: hall of fame

![](etc/img/cf.png)

Idea of this project is find interesting chess games and to form my own 'Top N' rating by various criterias (see below)

Used tools:

* [lichess database](https://database.lichess.org/) 
* My fork of [chesspresso java lib](https://github.com/mark-dev/chesspresso)
* [Spring](https://spring.io/)
* [Clickhouse](https://clickhouse.yandex/)

    
I scan lichess database and there is results:

1. [Longest uncrossed knight path in real games](/etc/results/knight-path.md)
   ![](etc/img/knight-path.png)
   You can read more here:
      
   [knight tour problem(wikipedia)](https://en.wikipedia.org/wiki/Knight%27s_tour)
   
   [longest uncrossed knight path(wikipedia)](https://en.wikipedia.org/wiki/Longest_uncrossed_knight%27s_path) 
   
2. [Games, where mate reaches by piece promotion, and winning side has less material](/etc/results/mate-by-pieces.md)
   ![](etc/img/knight-promo-mate.png)
   [game from picture](https://lichess.org/oKgxoBoi)
   
   [Results here]()
3. [What the strongest knight fork? (by fork-ed material)](etc/results/fork-material.md))  
   ![](etc/img/knight-fork.png)
   
4. Top games by average ranged pieces(B,R,Q) capture distance:
    ![](etc/img/ranged-capture-distance.png)
5. Take look for most valuable piece
    
   (Yes, need filtering games by hand, to avoid giveaway games like: https://lichess.org/IltUctI4 look on c7 pawn, future mvp queen :)) 
   
6. [Positions, where side can do a lot of captures?](etc/results/capture-sharpness.md)
   ![](etc/img/capture-sharpness.png)
   [Game from picture](https://lichess.org/shIhqTdO#68)
    
7. [Positions where 8 pieces stay on one board-column](etc/results/8-pieces-one-column.md)
    ![](etc/img/one-column-pieces.png)
    

8. Mate by en-passant move: 

9. 
   

