
隐藏图标方案一：     <data android:host="AuthActivity" android:scheme="com.android.example" />
隐藏图标方案二：      <data android:host="Toby" android:scheme="包名" />

隐藏进程一：android:excludeFromRecents="true"

熄屏广告:参考 ScreenStatusController

逻辑--》app初始化：监听屏幕状态--》锁屏打开Activity，Activity添加window（为了屏蔽home按键），==>具体逻辑处理

