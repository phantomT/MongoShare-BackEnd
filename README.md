# 工程实践项目
项目资源共享平台
1. 修改一些实体中的userid为username，因为系统使用用户名登录并且没有允许重名，所以用户名唯一
2. kkFileView需要开启FTP，并在配置文件中修改登录FTP的用户名和密码
3. 分片文件预览未完成，doc等文件可以预览但是docx就不行，不知道为什么
4. MongoDB需要新建一个数据库，并在配置文件中修改对应信息，在运行前开启MongoDB
5. 在运行前要开启**MongoDB Redis KKFileView以及FTP服务**，并启动前端程序