//package org.jeecg;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import org.junit.Test;
//
///**
// * <p>
// * 测试生成代码
// * </p>
// *
// * @author K神
// * @date 2017/12/18
// */
//public class GeneratorServiceEntityGloble {
//
//    //是否强制生成mp字段注解，true=强制生成
//    boolean tableFieldAnnotationEnabled = false;
//    @Test
//    public void generateCode() {
//        String packageName = "org.jeecg.module.notice";
//        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
//
//        generateByTables(serviceNameStartWithI, packageName, "service_dynamic_info");
//    }
//
//    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
//        GlobalConfig config = new GlobalConfig();
//        String dbUrl = "jdbc:mysql://115.29.233.195:3306/healthytest";
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setDbType(DbType.MYSQL)
//                .setUrl(dbUrl)
//                .setUsername("root")
//                .setPassword("!L6532r&")
//                .setDriverName("com.mysql.jdbc.Driver");
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//                .setCapitalMode(true)
//                .setEntityLombokModel(false).setTablePrefix(new String[]{"to_e_"})
////                .setDbColumnUnderline(true)
//                .entityTableFieldAnnotationEnable(tableFieldAnnotationEnabled)
//                .setNaming(NamingStrategy.underline_to_camel)
//                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
//        config.setActiveRecord(false)
//                .setAuthor("DexSinis")
//                .setOutputDir("/Users/dexsinis/D/E/Lcnet/yewyw-lcnet/yewyw-mybatis/src/main/java/")
//                .setFileOverride(false);
//        if (!serviceNameStartWithI) {
//            config.setServiceName("%sService");
//        }
//        new AutoGenerator().setGlobalConfig(config)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(
//                        new PackageConfig()
//                                .setParent(packageName)
//                                .setController("controller")
//                                .setEntity("entity")
//                ).execute();
//    }
//
//    private void generateByTables(String packageName, String... tableNames) {
//        generateByTables(true, packageName, tableNames);
//    }
//}
