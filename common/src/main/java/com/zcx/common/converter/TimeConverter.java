package com.zcx.common.converter;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import com.zcx.common.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.common.converter
 * @ClassName: TimeConverter
 * @Author: loafer
 * @Description:
 * @Date: 2020/8/18 13:36
 * @Version: 1.0
 */
@Log4j2
public class TimeConverter implements WriteConverter {
    @Override
    public String convert(Object value) {
        if (value == null) {
            return StringUtils.EMPTY;
        } else {
            try {
                return DateUtil.formatCSTTime(value.toString(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            } catch (ParseException e) {
                String message = "时间转换异常";
                log.error(message, e);
                throw new ExcelKitWriteConverterException(message);
            }
        }
    }
}
