
隐藏图标方案一：     <data android:host="AuthActivity" android:scheme="com.android.example" />
隐藏图标方案二：      <data android:host="Toby" android:scheme="包名" />

隐藏进程一：android:excludeFromRecents="true"

熄屏广告:参考 ScreenStatusController

逻辑--》app初始化：监听屏幕状态--》锁屏打开Activity，Activity添加window（为了屏蔽home按键），==>具体逻辑处理

android 10.0 描述：

限制了在启动器中隐藏应用图标的功能。除非满足以下任一条件，否则应用必须具有图标：
它是系统应用，即使是更新后的应用。
它是托管式配置文件管理应用（工作资料所有者）。
它未请求任何权限。
它不包含任何组件（例如，Activity、内容提供程序、广播接收器和服务）。
具有图标且拥有已启用的可启动 Activity 的应用不受影响。除了上面列出的例外情况，所有应用均会显示一个图标。
如果应用没有图标，则会显示默认的系统图标。点按没有可启动 Activity 的应用图标时会打开应用信息屏幕。

