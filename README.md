## Chessfactory: hall of fame
![](etc/img/cf.png)

Originally, [Chessfactory](https://github.com/mark-dev/chessfactory) is my study web chess project, inspired by [lichess](https://lichess.org).

Idea of this project is find interesting chess games and to form my own 'Top N' rating by various criterias (see below).

Project has same name prefix, because it also study and inspired by lichess :)

Used tools:

* [lichess database](https://database.lichess.org/) 
* My fork of [chesspresso java lib](https://github.com/mark-dev/chesspresso)
* [Spring](https://spring.io/)
* [Clickhouse](https://clickhouse.yandex/)

    
I scan lichess database(602 million games, at march 2019) and there is results:

1. [Longest uncrossed knight path in real games](/etc/results/knight-path.md)

    ![](etc/img/knight-path.png)
    
    [read more about this problem(wikipedia)](https://en.wikipedia.org/wiki/Longest_uncrossed_knight%27s_path)
       
2. [Games, where mate reaches by piece promotion, and winning side has less material](/etc/results/mate-by-pieces.md)
   
   ![](etc/img/knight-promo-mate.png)
   
   [game from picture](https://lichess.org/oKgxoBoi)
   
3. [What the strongest knight fork? (by fork-ed material)](etc/results/fork-material.md)
  
   ![](etc/img/knight-fork.png)
   
4. [Top games by average ranged pieces(B,R,Q) capture distance](etc/results/ranged-capture-distance.md):
    
    ![](etc/img/ranged-capture-distance.png)
   
5. [Positions, where side can do a lot of captures?](etc/results/capture-sharpness.md)

   ![](etc/img/capture-sharpness.png)
   
   [Game from picture](https://lichess.org/shIhqTdO#68)
    
6. [Positions where 8 pieces stay on one board-column](etc/results/8-pieces-one-column.md)

    ![](etc/img/one-column-pieces.png)
    
7. [Mate after en passant move](etc/results/ep-mate.md) 

    ![](etc/img/ep-mate.png)
    
   

