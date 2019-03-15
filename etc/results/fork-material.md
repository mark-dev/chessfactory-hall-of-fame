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

┌─directLinkMove───────────────────┬─maxForkMaterialAmount─┬─blackElo─┬─whiteElo─┬─maxForkMaterialAmountPieces───┐
│ https://lichess.org/D9UDBXE9#60  │                  3525 │     1779 │     1585 │ ['R','K','R','B','Q','N','P'] │
│ https://lichess.org/SnpzaXEw#55  │                  3525 │     1875 │     1898 │ ['N','P','Q','B','K','R','R'] │
│ https://lichess.org/9g9gNGeV#62  │                  3525 │     1870 │     1857 │ ['R','R','Q','K','P','N','B'] │
│ https://lichess.org/OINuA3xS#56  │                  3525 │     1861 │     1885 │ ['R','K','B','N','P','R','Q'] │
│ https://lichess.org/eZ8Dn2Nz#35  │                  3425 │     1715 │     1713 │ ['N','B','Q','K','R','R']     │
│ https://lichess.org/fsbzqMKX#46  │                  3425 │     1836 │     1824 │ ['R','R','N','K','Q','B']     │
│ https://lichess.org/vFUw03ZK#51  │                  3425 │     1401 │     1401 │ ['B','N','Q','K','R','R']     │
│ https://lichess.org/pDYjUxVU#40  │                  3425 │     1912 │     2051 │ ['R','R','Q','K','N','B']     │
│ https://lichess.org/xMjLZc6n#38  │                  3425 │     1098 │     1165 │ ['R','K','R','B','Q','N']     │
│ https://lichess.org/Lkjjvywf#38  │                  3425 │     1698 │     1797 │ ['R','R','Q','K','N','B']     │
│ https://lichess.org/Oh3FDJAb#66  │                  3425 │     1859 │     1696 │ ['K','R','Q','R','N','B']     │
│ https://lichess.org/wMTfwVAU#55  │                  3425 │     1628 │     1505 │ ['B','N','Q','K','R','R']     │
│ https://lichess.org/mJ1c1NtW#43  │                  3425 │     1540 │     1939 │ ['B','N','Q','K','R','R']     │
│ https://lichess.org/MsrUxqsX#40  │                  3425 │     1143 │     1161 │ ['K','R','Q','B','N','R']     │
│ https://lichess.org/DfPvI6Vc#50  │                  3425 │     1409 │     1404 │ ['R','R','Q','K','B','N']     │
│ https://lichess.org/4tuJdXPU#56  │                  3425 │     2209 │     2189 │ ['R','R','Q','B','N','K']     │
│ https://lichess.org/gvuoSFOo#57  │                  3425 │     1573 │     1554 │ ['R','N','Q','B','R','K']     │
│ https://lichess.org/1KuVfes7#50  │                  3425 │     1290 │     1434 │ ['R','R','K','Q','B','N']     │
│ https://lichess.org/u4G17uPm#55  │                  3425 │     1904 │     2031 │ ['B','N','Q','R','R','K']     │
│ https://lichess.org/yCpmsc7Z#54  │                  3425 │     2016 │     1990 │ ['R','K','Q','N','R','B']     │
│ https://lichess.org/smUpdmbG#57  │                  3400 │     1846 │     1628 │ ['N','N','Q','K','R','R']     │
│ https://lichess.org/ojvPtPeg#43  │                  3400 │     1820 │     1614 │ ['N','R','N','R','Q','K']     │
│ https://lichess.org/EpCNcQB0#47  │                  3400 │     1568 │     1594 │ ['N','N','Q','K','R','R']     │
│ https://lichess.org/1fqgm4Uy#29  │                  3325 │     1416 │     1426 │ ['N','N','B','P','K','Q','R'] │
│ https://lichess.org/pmCzBvRT#26  │                  3325 │     1620 │     1672 │ ['Q','R','P','K','B','N','N'] │
│ https://lichess.org/jM22s2CR#42  │                  3325 │     1520 │     1368 │ ['R','K','N','P','B','Q','N'] │
│ https://lichess.org/AS3n51E0#36  │                  3325 │     1394 │     1405 │ ['R','Q','P','K','B','N','N'] │
│ https://lichess.org/3kyHJUPC#31  │                  3325 │     1736 │     1726 │ ['N','B','N','Q','P','R','K'] │
│ https://lichess.org/UMlFLmmS#44  │                  3300 │     1638 │     1661 │ ['R','R','K','Q','P','N','P'] │
│ https://lichess.org/LvV0xcq6#50  │                  3300 │     1581 │     1488 │ ['R','K','N','P','R','Q','P'] │
│ https://lichess.org/v7Hkfgv7#79  │                  3300 │     1654 │     1540 │ ['P','P','N','Q','K','R','R'] │
│ https://lichess.org/v6rI1v4X#66  │                  3300 │     1504 │     1539 │ ['R','K','R','P','Q','P','N'] │
│ https://lichess.org/JJYP5qHX#50  │                  3300 │     1269 │     1303 │ ['K','R','P','P','R','N','Q'] │
│ https://lichess.org/Rf2MYCis#230 │                  3300 │     1609 │     1451 │ ['N','Q','N','Q','Q']         │
│ https://lichess.org/F7BseTe3#51  │                  3300 │     1520 │     1598 │ ['N','P','P','Q','K','R','R'] │
│ https://lichess.org/BMZ6OpKS#65  │                  3300 │     1414 │     1381 │ ['P','Q','N','R','P','R','K'] │
│ https://lichess.org/361jGBll#58  │                  3300 │     1436 │     1497 │ ['R','R','Q','K','P','P','N'] │
│ https://lichess.org/jXQ3RbzY#42  │                  3300 │     1858 │     1857 │ ['R','K','N','R','P','P','Q'] │
│ https://lichess.org/RCXyKUiI#71  │                  3300 │     1414 │     1397 │ ['P','N','Q','R','P','R','K'] │
│ https://lichess.org/WfV4RyhK#69  │                  3300 │     1995 │     1983 │ ['P','P','N','Q','K','R','R'] │
│ https://lichess.org/zDlHO7nV#73  │                  3300 │     1623 │     1845 │ ['P','N','P','Q','K','R','R'] │
│ https://lichess.org/MhQvb9XS#46  │                  3300 │     1684 │     1769 │ ['R','K','Q','P','P','N','R'] │
│ https://lichess.org/6ZukjcGG#64  │                  3300 │     1986 │     2060 │ ['R','R','Q','K','P','N','P'] │
│ https://lichess.org/wYs5XLuF#54  │                  3300 │     1966 │     2120 │ ['R','R','Q','K','N','P','P'] │
│ https://lichess.org/Yyu56kzR#59  │                  3300 │     1622 │     2051 │ ['R','P','P','Q','K','R','N'] │
│ https://lichess.org/oxniYkw2#61  │                  3300 │     1309 │     1404 │ ['N','P','R','Q','P','K','R'] │
│ https://lichess.org/Y8ir3gU5#54  │                  3300 │     1958 │     1948 │ ['R','R','Q','K','P','P','N'] │
│ https://lichess.org/U8vYnjSe#50  │                  3300 │     2413 │     2357 │ ['R','K','Q','R','P','N','P'] │
│ https://lichess.org/LmzlTZx7#38  │                  3300 │     1743 │     1859 │ ['R','R','Q','K','P','N','P'] │
│ https://lichess.org/BCOMSkxt#54  │                  3300 │     2073 │     2146 │ ['R','R','Q','K','P','P','N'] │
│ https://lichess.org/kaOdAK5a#54  │                  3300 │     1915 │     1900 │ ['R','R','Q','K','N','P','P'] │
│ https://lichess.org/F8w4hpVU#53  │                  3300 │     2050 │     1979 │ ['P','N','Q','R','P','R','K'] │
│ https://lichess.org/OD8EcQDc#68  │                  3300 │     1841 │     1846 │ ['R','K','P','P','N','R','Q'] │
│ https://lichess.org/uZaidubE#32  │                  3225 │     1186 │     1800 │ ['K','N','B','R','N','Q']     │
│ https://lichess.org/WpVm549Q#67  │                  3225 │     1861 │     1847 │ ['N','Q','B','N','R','K']     │
│ https://lichess.org/HZjSIiNn#44  │                  3225 │     1498 │     1500 │ ['K','R','Q','B','N','N']     │
│ https://lichess.org/37sKk6Tk#53  │                  3225 │     2228 │     2059 │ ['Q','N','N','B','R','K']     │
│ https://lichess.org/Je2w9juR#38  │                  3225 │     1728 │     1674 │ ['R','K','N','Q','N','B']     │
│ https://lichess.org/AO3hkIAM#47  │                  3225 │     1368 │     1368 │ ['Q','N','N','B','K','R']     │
│ https://lichess.org/UDjJSo48#69  │                  3225 │     1717 │     1537 │ ['Q','N','N','B','R','K']     │
│ https://lichess.org/ghUvgcxd#40  │                  3225 │     2193 │     1686 │ ['R','K','Q','N','B','N']     │
│ https://lichess.org/qwtyjH91#40  │                  3225 │     1883 │     2180 │ ['R','K','B','N','Q','N']     │
│ https://lichess.org/E3m4Pvn1#40  │                  3225 │     1732 │     1520 │ ['R','K','Q','N','N','B']     │
│ https://lichess.org/odELHch4#42  │                  3225 │     1821 │     1918 │ ['R','N','Q','K','N','B']     │
│ https://lichess.org/YgDluG8F#42  │                  3225 │     1779 │     1282 │ ['R','K','Q','N','N','B']     │
│ https://lichess.org/2c01rdAG#41  │                  3225 │     1488 │     1479 │ ['N','N','B','K','Q','R']     │
│ https://lichess.org/sbErvX4l#43  │                  3225 │     2060 │     2046 │ ['Q','N','B','N','R','K']     │
│ https://lichess.org/7lFyKrcZ#27  │                  3225 │     1523 │     1481 │ ['N','N','Q','B','K','R']     │
│ https://lichess.org/vVhPM0te#51  │                  3225 │     1241 │     1472 │ ['N','N','B','K','Q','R']     │
│ https://lichess.org/030PrK5M#36  │                  3225 │     1358 │     1329 │ ['R','K','N','N','B','Q']     │
│ https://lichess.org/8AhvioCY#44  │                  3200 │     1207 │     1387 │ ['R','K','N','P','Q','R']     │
│ https://lichess.org/E1fCq2Yb#34  │                  3200 │     1592 │     1729 │ ['R','R','K','P','Q','N']     │
│ https://lichess.org/D6mHw0Zn#52  │                  3200 │     1275 │     1307 │ ['K','R','P','Q','N','R']     │
│ https://lichess.org/vC3FQwWK#47  │                  3200 │     1920 │     1820 │ ['N','Q','R','P','R','K']     │
│ https://lichess.org/r1m1ZUaZ#51  │                  3200 │     1779 │     1603 │ ['N','Q','P','K','R','R']     │
│ https://lichess.org/pwzeSSmv#44  │                  3200 │     1503 │     1412 │ ['R','K','Q','R','P','N']     │
│ https://lichess.org/gzyjt2VP#68  │                  3200 │     2062 │     2075 │ ['R','R','Q','K','P','N']     │
│ https://lichess.org/nl1ExZ2w#44  │                  3200 │     1962 │     1958 │ ['R','R','P','K','N','Q']     │
│ https://lichess.org/9ILU2Eds#43  │                  3200 │     1171 │     1357 │ ['N','R','Q','P','R','K']     │
│ https://lichess.org/xTuxqCEj#58  │                  3200 │     1515 │     1420 │ ['R','K','Q','P','N','R']     │
│ https://lichess.org/vOnZQiJQ#52  │                  3200 │     1254 │     1246 │ ['R','K','P','R','Q','N']     │
│ https://lichess.org/RY0Qfjsy#37  │                  3200 │     1787 │     1763 │ ['P','N','Q','K','R','R']     │
│ https://lichess.org/7V8QoSLo#35  │                  3200 │     1261 │     1298 │ ['N','P','Q','K','R','R']     │
│ https://lichess.org/d3cNzYMP#41  │                  3200 │     1300 │     1261 │ ['P','N','Q','K','R','R']     │
│ https://lichess.org/ETy6hxFE#39  │                  3200 │     1692 │     1645 │ ['R','Q','N','P','R','K']     │
│ https://lichess.org/pbUdq2Vk#37  │                  3200 │     2043 │     2154 │ ['N','P','Q','K','R','R']     │
│ https://lichess.org/oSNPUeCC#65  │                  3200 │     1457 │     1529 │ ['N','P','K','Q','R','R']     │
│ https://lichess.org/8HFotOMw#35  │                  3200 │     1626 │     1653 │ ['N','P','Q','K','R','R']     │
│ https://lichess.org/pXExoO6f#56  │                  3200 │     1464 │     1546 │ ['R','K','R','Q','P','N']     │
│ https://lichess.org/Ud3NO4EL#42  │                  3200 │     1829 │     1917 │ ['R','R','Q','K','P','N']     │
│ https://lichess.org/WBVPgFI4#52  │                  3200 │     1949 │     2008 │ ['R','R','K','P','N','Q']     │
│ https://lichess.org/sPaLvm7u#35  │                  3200 │     1966 │     2125 │ ['N','P','Q','K','R','R']     │
│ https://lichess.org/toRXPXxQ#42  │                  3200 │     1557 │     1624 │ ['R','K','Q','N','R','P']     │
│ https://lichess.org/XmEbeCbi#42  │                  3200 │     1224 │     1228 │ ['R','R','P','K','Q','N']     │
│ https://lichess.org/daXWZu9e#42  │                  3200 │     1947 │     1900 │ ['R','R','Q','K','N','P']     │
│ https://lichess.org/0UKj4Azd#67  │                  3200 │     1735 │     1779 │ ['N','P','R','Q','R','K']     │
│ https://lichess.org/gY2xqMlk#33  │                  3200 │     1408 │     1371 │ ['N','Q','P','K','R','R']     │
│ https://lichess.org/ydc52Dtg#56  │                  3200 │     1571 │     1555 │ ['R','R','Q','K','N','P']     │
│ https://lichess.org/cz7uJQ9F#58  │                  3200 │     1677 │     1645 │ ['R','Q','K','P','R','N']     │
│ https://lichess.org/JGaSdbI2#42  │                  3200 │     1546 │     1949 │ ['R','R','P','K','Q','N']     │
└──────────────────────────────────┴───────────────────────┴──────────┴──────────┴───────────────────────────────┘

