# Android toolbar

大家对android toolbar应该很熟悉了，但是你们有没有利用toolbar来制作全栏工具栏，也就是整个toolbar全部显示button,相应不多，先上效果图。

![avatar](images/1.jpg)

- 通过ActionMenuView可以实现自由布局
- 应用的主题设置成NoTitle主题
- activity中实现菜单解析
- menu布局文件中showAsAction全部设置成always
- 根据toolbar的宽度设置ifRoom的个数
