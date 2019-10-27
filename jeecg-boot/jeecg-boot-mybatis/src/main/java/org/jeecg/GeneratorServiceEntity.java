package org.jeecg;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * <p>
 * 测试生成代码
 * </p>
 *
 * @author K神
 * @date 2017/12/18
 */
public class GeneratorServiceEntity {

//    tp_pricing_package 修改
//    tp_pricing_pkg_res 新增
//    consulter_pricing_package 新增
//    consulter_order_pkg_pay 新增
//    consulter_vip_card 新增
//    consulter_vip 修改

    //是否强制生成mp字段注解，true=强制生成
    boolean tableFieldAnnotationEnabled = false;
    @Test
    public void generateCode() {
        String packageName = "org.jeecg.modules.sims";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService


        generateByTables(serviceNameStartWithI, packageName, "t_sys_log");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://106.14.211.207:3306/petrusplus";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true).setTablePrefix(new String[]{"tp1_"})
//                .setDbColumnUnderline(true)
                .entityTableFieldAnnotationEnable(tableFieldAnnotationEnabled)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(true)
                .setAuthor("DexSinis").setDateType(DateType.ONLY_DATE)
       // /Users/lcn-mac/pro/interface_zx/yewyw-mybatis/src/main/java
                .setOutputDir("/Users/dexsinis/D/E/cbk/Petrus/yewyw-mybatis/src/main/java/")
//                .setOutputDir("/Users/lcn-mac/pro/interface_zx/yewyw-mybatis/src/main/java/")
                .setFileOverride(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
