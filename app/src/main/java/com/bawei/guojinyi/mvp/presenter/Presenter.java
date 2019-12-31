package com.bawei.guojinyi.mvp.presenter;

import com.bawei.guojinyi.contract.ContractClass;
import com.bawei.guojinyi.mvp.module.Module;

/**
 * ClassName: weektest01
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2019/12/31 9:03
 * @Description: 用途：完成特定功能
 */
public class Presenter implements ContractClass.ContractPresenter {

    @Override
    public void attachView(String path, final ContractClass.ContractView contractView) {
        Module module = new Module();
        module.getData(path, new ContractClass.ContractModule.CallBack() {
            @Override
            public void success(String json) {
                contractView.viewSuccess(json);
            }

            @Override
            public void failed(String failed) {
                contractView.viewFailed(failed);
            }
        });
    }
}
