package com.bawei.guojinyi.mvp.module;

import com.bawei.guojinyi.contract.ContractClass;
import com.bawei.guojinyi.utils.NetUtil;

/**
 * ClassName: weektest01
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2019/12/31 9:03
 * @Description: 用途：完成特定功能
 */
public class Module implements ContractClass.ContractModule {
    //拿数据
    @Override
    public void getData(String path, final CallBack callBack) {
        NetUtil instance = NetUtil.getInstance();
        instance.dogetData(path, new NetUtil.DataCallBack() {
            @Override
            public void success(String json) {
                callBack.success(json);
            }

            @Override
            public void failed(String failed) {
                callBack.failed(failed);
            }
        });
    }

}
