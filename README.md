# Ayanami

Ayanami 是VirtualJudge的主要子系统，提供了题目，比赛，讨论，聚合，用户的实现。题目有关功能上依赖于 [Makinami](https://github.com/Coderhypo/makinami)。

## 技术栈

 + Spring
 + Spring MVC
 + Spring Data Jpa
 + Hibernate
 + MySQL
 + Redis

## 模块

### 题目

题目使用Makinami的题库，并不负责题库的更新，题目的提交以及评判结果的返回依赖Makinami的API

但本程序会保存Makinami中题库的题目Title，并以此为依据进行数据统计以及标签关联。

### 比赛

提供多种类型比赛并提供积分制。

### 讨论

基于标签/话题的轻量论坛，通过标签，把题目，讨论，聚合关联。

### 聚合

聚合用户博客更新（Feed），提供用户博客的订阅。
