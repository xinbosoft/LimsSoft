package cn.xinbo.algorithm;

import java.util.Arrays;

/**
 * 顺序线性表的实现
 * @author lu
 *
 */
public class LineList<E> {

	private int size;
	private Object[] array;
	private final int default_length = 16;
	/**
	 * 构造方法
	 */
	public LineList(){
		size = 0;
		array = new Object[default_length];
	}
	/**
	 * 指定长度进行构造
	 * @param length
	 * @throws IllegalAccessException
	 */
	public LineList(int length) throws IllegalAccessException{
		if(length<0){
			throw new IllegalAccessException("初始长度不合格：" + length);
		}
		array=new Object[length];
	}
	public LineList(E element , int length){
		if(length<1){
			throw new IllegalArgumentException("初始长度不合法:"+length); 
		}
		array=new Object[length];
		array[0] = element;
		size++;
	}
	/**
	 * 指定初始化元素
	 * @param element
	 */
	public LineList(E element){
		array=new Object[default_length];
		array[0] = element;
	}
	/**
	 * 获取元素个数
	 * @return
	 */
	public int size(){
		return size;
	}
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	/**
	 * 判断是否包含此元素
	 * @param e
	 * @return
	 */
	public boolean contains(E e){
		if(indexOf(e)==-1){
			return false;
		}
		return true;
	}
	/**
	 * 格式化数组
	 * @return
	 */
	public Object[] toArray(){
		return Arrays.copyOf(array, size);
	}
	/**
	 * 向线性表尾添加元素
	 * @param e
	 */
	public void add(E e){
		extendCapacity(size+1);
		array[size]=e;
		size++;
	}
	/**
	 * 扩容
	 * @param length
	 */
	public void extendCapacity(int length){
		int minCapacity = Math.max(array.length, length);
		if (minCapacity - array.length>0){
			int newLength = array.length + array.length/2;
			if(newLength < minCapacity){
				newLength = minCapacity;
			}
			if(newLength > Integer.MAX_VALUE-8){
				newLength = Integer.MAX_VALUE;
			}
			array = Arrays.copyOf(array, newLength);
		}
	}
	/**
	 * 从线性表中删除元素
	 * @param e
	 */
	public void removeAll(E e){
		if(e == null){
			for (int i = 0; i < size; i++) {
				if(array[i]==null){
					fastRemove(i);
				}
			}
		}else{
			for (int i = 0; i < size; i++) {
				if(e.equals(array[i])){
					fastRemove(i);
				}
			}
		}
	}
	/**
	 * 删除索引处元素
	 * @param index
	 */
	private void fastRemove(int index) {
		
		if(size-index-1>0){
			System.arraycopy(array, index+1, array,index, size-1);
		}
		array[--size]=null;
	}
	/**
	 * 清空线性表
	 */
	public void clear(){
		Arrays.fill(array, null);
		size=0;
	}
	/**
	 * 获得索引处元素
	 * @param index
	 * @return
	 */
	public E get(int index){
		checkIndex(index);
		return (E) array[index];
	}
	/**
	 * 验证索引是否越界
	 * @param index
	 */
	private void checkIndex(int index) {
		
		if(index>=size || index<0){
			throw new IndexOutOfBoundsException("索引越界");
		}
	}
	public E set(int index,E element){
		checkIndex(index);
		E e = (E)array[index];
		array[index]=element;
		return e;
	}
}
