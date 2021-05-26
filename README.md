# Clockをシステムプロパティで指定する

## 設定方法

clock.type = fixed | current
clock.value = yyyy-mm-dd

## 使い方

DateContext.now() ;

- clock.typeがfixedの場合、clock.valueで指定した日付を返す:LocalDate.now(Clock.fixed())
- clock.typeがcurrentの場合、現在の日付を返す: LocalDate.now()
