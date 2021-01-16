package info.lostred.blog;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取项目路径
        String path = System.getProperty("user.dir");
        //设置代码输出目录
        gc.setOutputDir(path + "/src/main/java");
        //设置作者
        gc.setAuthor("lostred");
        //去除servicesI前缀
        gc.setServiceName("%sService");
        //主键生成策略使用自动增长
        gc.setIdType(IdType.AUTO);
        // 打开文件
        gc.setOpen(false);
        // 文件覆盖
        gc.setFileOverride(true);
        //使用Swagger(接口文档用)
        gc.setSwagger2(true);
        generator.setGlobalConfig(gc);

        //2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://lostred.info:3306/blog?useUnicode=true&characterEncoding=utf8");
        dsc.setUsername("blog");
        dsc.setPassword("123456");
        generator.setDataSource(dsc);

        //3.设置包名
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("info.lostred");
        //设置Entity包名，默认是entity
        pc.setEntity("entity");
        //设置Mapper包名
        pc.setMapper("mapper");
        //设置Service包名
        pc.setService("service");
        //设置Controller包名
        pc.setController("controller");
        generator.setPackageInfo(pc);

        //4.策略设置
        StrategyConfig sc = new StrategyConfig();
        sc.setTablePrefix("t");
        //设置要映射的表名，参数为数据库表名，每张表用,分隔
        sc.setInclude( "admin", "admin_log", "article", "catalogue", "status", "user","user_log");
        //设置下划线转驼峰
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //使用Lombok注解
        sc.setEntityLombokModel(true);
        //使用RestController注解
        sc.setRestControllerStyle(true);
        //设置逻辑删除列
        sc.setLogicDeleteFieldName("deleted");
        //自动填充配置
        TableFill gmt_create = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill deleted = new TableFill("deleted", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(deleted);
        tableFills.add(gmt_modified);
        sc.setTableFillList(tableFills);
        //乐观锁
//        sc.setVersionFieldName("version");
        generator.setStrategy(sc);
        generator.execute();
    }
}
