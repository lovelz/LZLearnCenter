package com.lovelz.lzlearncenter.memory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lovelz.lzlearncenter.R;
import com.lovelz.lzlearncenter.memory.data.DataTypeActivity;
import com.lovelz.lzlearncenter.memory.design.MemoryDesignActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lovelz
 * @date on 2018/11/2.
 */
public class MemoryOptimizeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_optimize);

        findViewById(R.id.enter_data_type).setOnClickListener(this);
        findViewById(R.id.enter_memory_design).setOnClickListener(this);


    }

    /**
     下面介绍一下Java四种级别的引用强度
     Normal:主要的引用类型，一般都用这，不被引用时会被回收；
     Soft:软引用（SoftReference）,垃圾回收器会根据系统对内存的需求，决定何时释放这些对象；
     Weak:和Soft引用类似，但是强度更弱（WeakReference）；
     Phantom:最弱的引用（PhantomReference）,所引用的对象是专门被用来回收的。
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter_data_type:
                startActivity(new Intent(this, DataTypeActivity.class));
                break;
            case R.id.enter_memory_design:
                startActivity(new Intent(this, MemoryDesignActivity.class));
                break;
        }
    }
}
