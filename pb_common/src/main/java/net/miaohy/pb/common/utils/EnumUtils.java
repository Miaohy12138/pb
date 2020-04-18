package net.miaohy.pb.common.utils;


import net.miaohy.pb.common.constant.ICodeEnum;

/**
 * @Author by Miaohy
 * @date 2019/12/20 11:30
 * @Version
 */
public class EnumUtils {
	public static <T extends ICodeEnum> T getByCode(Integer code, Class<T> enumClass) {
		//通过反射取出Enum所有常量的属性值
		for (T each: enumClass.getEnumConstants()) {
			//利用code进行循环比较，获取对应的枚举
			if (code.equals(each.getCode())) {
				return each;
			}
		}
		return null;
	}
}
