package com.bawei.guojinyi.contract;

/**
 * ClassName: weektest01
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2019/12/31 8:59
 * @Description: 用途：契约类
 */
public interface ContractClass {

    //View层
    interface ContractView{
        void viewSuccess(String json);
        void viewFailed(String failed);
    }
    //module层
    interface ContractModule{
        public void getData(String path,CallBack callBack);
        public interface CallBack{
            void success(String json);
            void failed(String failed);
        }
    }
    //presenter层
    interface ContractPresenter{
        public void attachView(String path,ContractView contractView);
    }
}
