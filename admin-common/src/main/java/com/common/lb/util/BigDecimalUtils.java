package com.common.lb.util;

import java.math.BigDecimal;

/**
 * Date: 2019-1-25
 * Description:
 */
public class BigDecimalUtils {
    public static BigDecimal setScaleDown(BigDecimal current, int position){
        return current.setScale(position,BigDecimal.ROUND_DOWN);
    }
}
