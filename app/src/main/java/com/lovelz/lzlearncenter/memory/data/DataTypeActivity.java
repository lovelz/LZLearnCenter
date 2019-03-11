package com.lovelz.lzlearncenter.memory.data;

import android.os.Bundle;
import android.util.SparseIntArray;

import com.lovelz.lzlearncenter.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lovelz
 * @date on 2018/11/2.
 */
public class DataTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_layout);
    }

    /**
     我们先来了解一下为java基本的数据类型所分配的内存（bit）:
         byte: 8 bit
         short: 16 bit
         int: 32 bit
         long: 64 bit
         float: 32 bit
         double: 64 bit
         boolean: 通常是 8 bit,具体的bit位取决于虚拟机
         char: 16 bit
     */
    private void dataTypeMemory(){
        //自动装箱
        Integer i = 0;
        //等同于Integer i = new Integer(0);


        //一个Integer对象需要16byte的内存空间，而基本类型只需16bit
        //当使用基本类型的包装类来声明一个变量时，对该变量所进行的任意操作都意味着至少会带来一个额外的对象分配。
        Integer integer = 0;
        integer ++;
        //我们来分析一下上面的代码流程
        //首先，integer的值是通过Integer对象得到的，接着将该值加1：int temp = integer.intValue() + 1;
        //然后，将得到的结果赋值给integer对象，也意味着需要一个新的装箱操作：integer = temp;
        //若直接使用基本类型，将没有必要进行装箱操作，就不会带来额外的内存分配。

    }

    /**
     * 我们可以使用Sparse数组来替代Map对象，避免发生自动装箱
     */
    private void userSparseArray() {
        SparseIntArray intArray = new SparseIntArray();
        intArray.put(2, 3);
        intArray.get(2);
    }
}
