
## 引入

project
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

app

```
dependencies {
        compile 'com.github.threekilogram:SystemUI:1.0'
}
```

## 设置状态栏颜色

```
//可设置所有颜色包括透明
SystemUI.setStatusColor(MainActivity.this, getColor("#ffd700"));
```

## 将view位移状态栏高度

```
SystemUI.fitStatusBarHeight(MainActivity.this, mIvLogo);
```

还原

```
SystemUI.doNotFitStatusBarHeight(MainActivity.this, mIvLogo);
```

## 半透明状态栏

```
SystemUI.translucentStatus(MainActivity.this);
```

或者通过设置颜色

## 全屏

```
SystemUI.layoutFullScreen(Activity activity)
```

## 沉浸式

```
SystemUI.immersiveSticky(MainActivity.this);
```

## 还原为普通模式

```
SystemUI.normal(Activity activity)
```