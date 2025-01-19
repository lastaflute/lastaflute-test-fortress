#
# LastaFlute における RabbitMQ を使った参考実装のREADME
#


/**
 * Consumer概要 
 */
pp クラス処理フロー

[アプリ起動]
1. FortressCurtainBeforeHook.java    // アプリ起動
 -> 2. AllRabbitMQPlanner.java       // Consmer定義と登録依頼
  -> 3. RabbitMQConsumerManager.java // Consmer登録と管理

[メッセージ受信]
3. RabbitMQConsumerManager.java      // 待ち受け(Job起動)
 -> 4. RabbitJobBase.java            // メッセージ受信したときの業務処理
  -> 5. Rabbit[xxx]Job.java          // その具象クラスたち



pp クラス全体像

[src/main/java]
 |-app
 |  |-job
 |  |  |-rabbit
 |  |  |  |-base
 |  |  |  |  |-RabbitJobBase.java         // 4. MQ Consumer業務処理Jobのスーパークラス
 |  |  |  |-land
 |  |  |  |  |-RabbitLandJob.java         // 5. メッセージを受け取ったときの業務処理をするJob
 |  |  |  |  |-RabbitLandMessageBody.java // JSON想定のメッセージ文字列の受け取りクラス
 |  |  |  |-sea
 |  |  |  |  |-RabbitSeaJob.java          // 5. LastaJobのJob形式で業務処理を実装する
 |  |  |  |  |-RabbitSeaMessageBody.java  // parse自体は RabbitJobBase.java にて
 |
 |-bizfw
 |  |-rabbitmq
 |  |  |-MQConnectionFactoryProvider.java // MQのConnectionFactoryの提供インターフェース
 |  |  |-RabbitJobResource.java           // MQ Consumer業務処理Jobへの共通的なパラメーター
 |  |  |-RabbitMQConsumerManager.java     // 3. MQ Consumerの登録/管理クラス (DIコンポーネント)
 |
 |-mylasta
 |  |-sponsor
 |     |-planer
 |     |  |-rabbitmq
 |     |     |-AllRabbitMQPlanner.java     // 2. queueの定義とMQ Consumerの登録依頼 (Managerを呼ぶ)
 |     |
 |     |-FortressCurtainBeforeHook.java    // 1. アプリ起動時のhook, ここでplannerを呼ぶ

[src/main/resource]
 |-app.xml         // Lasta Di の Di xml のルート、ここで rabbit_mq.xml を include
 |-rabbit_mq.xml   // 主に、RabbitMQConsumerManager をDIコンポーネントとして登録



pp 参考実装の特徴
o Consumer の起動/登録は CurtainBeforeHook から

o キューの定義は Planner
 i 具体的なキューの名前とか
 i キューを追加するときなどはここを修正

o キューの登録処理は Manager
 i できるだけ汎用的な仕組みロジック
 i 仕組みの振る舞いを変えるときだけ修正
 i これはDIコンポーネントなので、rabbit_mq.xml で定義



pp 参考実装の但し書き
o 接続設定やmock設定などはひとまず決め打ちで実装している
 i [app]_env.properties に切り出して設定したほうが良い

o RabbitMQのオプションなどもひとまず決め打ちで実装している
 i 現場の要件に合わせてtrue/falseなど実装を修正した方が良い

o #genba_fitting でソースコード検索して、現場でフィッティングした方が良い箇所を確認すること

o UnitTestでも起動してしまうので、ローカルでRabbitMQサーバーを準備しない開発の場合は...
 i [app]_env.properties で RabbitMQ を有効/無効にするbooleanプロパティを定義して...
 i CurtainBeforeHook で判定させると良い (一例として)





/**
 * Producer概要 
 */
pp こっちまだ作ってない

