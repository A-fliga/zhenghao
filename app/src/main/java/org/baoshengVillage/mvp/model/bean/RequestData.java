package org.baoshengVillage.mvp.model.bean;

/**
 * Created by zhiqiang on 3/16/17.
 */
public class RequestData<T> {

    /**
     * status : {"code":0,"message":"success"}
     * bean : {"username":"756983768b365fac449f8fb4196757ec232e","password":null,"gender":null,"mobile":"8613880717569","email":null,"avatarBucket":null,"avatarName":null,"description":null}
     */
    public StatusBean status;
    public T data;

    @Override
    public String toString() {
        return "RequestData{" +
                "status=" + status +
                ", bean=" + data +
                '}';
    }

    public static class StatusBean<T> {

        /**
         * code : 0
         * message : success
         */
        public int code;
        public T message;

        @Override
        public String toString() {
            return "StatusBean{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}