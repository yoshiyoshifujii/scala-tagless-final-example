# scala-tagless-final-example

ScalaでTagless Final StyleにRepositoryを設計した際に、Higher Kindが異なるRepositoryを、良い感じにUse Caseに実装する手法についてまとめました。

```
$ sbt test
```

で試せます。

# 構成

## domain

ドメイン駆動設計の文脈における、ドメイン層にあたり、今回は、例なので、適当なUserとかAccountを作っておいてます。

## repositories

ドメイン駆動設計の文脈における、永続化機構となるRepositoryを置いてます。UserとAccountをそれぞれ永続化したり、再生成したりする責務を持ちます。

## usercases

Clean Architectureの文脈における、ユースケース層です。アプリケーション固有のビジネスルールを表現します。

domain, repositories, usecasesは、RDBとか、WEBとか、そういった詳細とは隔離して、方針を表現することに注力します。

なので、依存性を排除します。

## adapters

RDBとか、Redisとか詳細を扱います。
