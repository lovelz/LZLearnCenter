package com.lovelz.lzlearncenter.http;

/**
 * @author lovelz
 * @date on 2018/10/18.
 */
public class HttpKnowledge {


    /**
     * Http相关知识
     *
     * 主要特点：
     * 1、支持C/S(客户/服务器)模式；
     * 2、简单快速，客户端向服务端请求时，只需传送请求方法和路径；
     * 3、灵活，允许允许传输任意类型的数据；
     * 4、无连接，限制每次连接只处理一个请求，服务器处理完客户端的请求，并收到客户的应答后，即断开连接，这样的方式可以节省传输时间；
     * 5、无状态，指协议对于事务处理没有记忆能力，意味着如果后续处理需要前面的信息，则需要重传，会增大传送数据体积，但是如果不需要前面的信息，应答就很快。
     *
     *
     * Http URL的格式如下：
     * http://host[":"port][abs_path]
     * http表示要通过http协议来进行通信；host表示合法的Internet主机域名或IP地址；port指定一个端口号，为空时默认为80；abs_path指定请求资源的URL(Web上任意可用资源)。
     *
     *
     * http请求报文：
     * 通常来说http请求报文由请求行、请求报头、空行、请求数据四个部分组成。
     *
     */

}
