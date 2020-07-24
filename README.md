# WhereIsPlugin
## なんですの？
自分用Spigotプラグイン<br>
今いる位置の名前が設定されている場合結果を表示してくれるプラグイン

## 動作環境は？
Spigot 1.16.1以降<br>
(あればMySQL 8(なくても動く))

## jarでよこせ。
まだ開発途上すぎてまともに動きません。

## 簡易コマンド説明
<pre>
/where -- 今いる位置を取得
/where add [name] [x] [z] [x] [z] -- 指定した範囲の名前を設定
/where del [name]                 -- 指定した設定されている名前を解除する
/where update [OldName] [NewName] -- 指定した今までの名前から新しい名前にする
/where help -- コマンドヘルプ
</pre>

## 設定ファイル
<pre>
# WhereIsPlugin SettingFile

# Language
ln: 'ja' (言語設定、あるだけで今の所何を設定しても変わらない。)

# MySQL Server
mysqlServer: '' (MySQLの接続先。使わない場合は空白)
mysqlUser: '' (MySQLのユーザー名)
mysqlPassWord: '' (MySQLのパスワード)
mysqlDatabase: '' (MySQLのデータベース名)
</pre>