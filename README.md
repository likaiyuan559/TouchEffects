# Android 点击特效TouchEffects

TouchEffects能够帮助你更快速方便的增加点击时候的效果，TouchEffects的目标是打造一个稳定、全面、且能更方便的自定义及个性化的一款点击效果框架。

## 功能特点：

- 只需要几行代码便能为全局的View加上点击效果

- 支持多种点击效果

- 支持个性化设置

- 支持View的独立效果

## 效果支持：

- Scale（点击缩放）

- Ripple（点击水波纹）

- Ripple_1（点击水波纹1）

- State（点击渐变）

- Shake（触碰抖动，用于不可点击时的反馈）

- 更多效果敬请期待

## 文档

- [属性文档](https://github.com/likaiyuan559/TouchEffects/blob/master/md/%E5%B1%9E%E6%80%A7%E6%96%87%E6%A1%A3.md)

- [个性化设置](https://github.com/likaiyuan559/TouchEffects/blob/master/md/%E4%B8%AA%E6%80%A7%E5%8C%96%E8%AE%BE%E7%BD%AE.md)

## 效果演示

| 缩放效果                                                                        | 水波纹效果                                                                        |
|:---------------------------------------------------------------------------:|:----------------------------------------------------------------------------:|
| ![](https://github.com/likaiyuan559/TouchEffects/blob/master/gif/scale.gif) | ![](https://github.com/likaiyuan559/TouchEffects/blob/master/gif/ripple.gif) |

| 渐变效果                                                                        | 个性化设置                                                                   |
|:---------------------------------------------------------------------------:|:-----------------------------------------------------------------------:|
| ![](https://github.com/likaiyuan559/TouchEffects/blob/master/gif/state.gif) | ![](https://github.com/likaiyuan559/TouchEffects/blob/master/gif/p.gif) |

## 简单用例

#### 1.设置maven仓库

```
allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

#### 2.添加依赖

```
implementation 'com.github.likaiyuan559:TouchEffects:0.1.1'
```

### 初始化设置

### 1.设置全局属性

```
public class MyApplication extends Application {

    static {
        TouchEffectsManager.build(TouchEffectsWholeType.SCALE)//设置全局使用哪种效果
                .addViewType(TouchEffectsViewType.ALL)//添加哪些View支持这个效果
                .setListWholeType(TouchEffectsWholeType.RIPPLE);//为父控件为列表的情况下，设置特定效果
    }
    ...

}
```

在BaseActivity中添加代码

```
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {

    TouchEffectsFactory.initTouchEffects(this);

    super.onCreate(savedInstanceState);

}
```

给View设置点击事件

```
view.setOnClickListener(this);
```

## 控件支持

- TextView

- Button

- ImageView

- ImageButton

- FrameLayout

- LinearLayout

- RelativeLayout

- ConstraintLayout

## 问题反馈

如果遇到问题，请提交[Issuse](https://github.com/likaiyuan559/TouchEffects/issues)，作者会每天都关注Github，并且会尽力解决每一个BUG，也欢迎大家能多提一些建议，多谢。

喜欢请给个Star
