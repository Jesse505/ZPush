
1. 在项目根目录 build.gradle 文件的 allprojects.repositories 块中，添加个推 maven 库地址 maven { url "http://mvn.gt.getui.com/nexus/content/repositories/releases/"}

2. 在 app/build.gradle 文件中的 android.defaultConfig 下指定所需的 CPU 架构，如下所示

   ```java
    ndk {
        abiFilters "armeabi", "armeabi-v7a"
    }
   ```

3. 在app的Manifest文件中的application节点中添加

   ```java
   <meta-data
       android:name="PUSH_APPID"
       android:value="${GETUI_APPID}" />
   ```

4. 在app/build.gradle 文件中添加

   ```java
   manifestPlaceholders = [
           GETUI_APPID     : "55sDpuDBks9T2KKSRi5vt6",
   ]
   ```

