package entity;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Bar {
	public static void main(String[] args) {
		CategoryDataset mDataset = createDataSet();		
		
		//创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		//设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		//设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		//应用主题样式
		ChartFactory.setChartTheme(mChartTheme);
		///////////////以上主题设置必须位于创建图表函数之前才能生效////////////
		
		JFreeChart chart = ChartFactory.createLineChart(
		//JFreeChart mBarChart = ChartFactory.createBarChart3D(
				"学校人员分布图", 	//图表标题
				"类型", 		//横轴（目录轴）标签
				"数量", 			//纵轴（数值轴）标签
				mDataset, 		//数据集
				PlotOrientation.VERTICAL,	//图表方向
				true, 			//是否显示图例
				true,			//是否生成提示工具
				false);			//是否生成url连接
		saveAsFile(chart, "D://bar.jpg", 600, 400);
//		ChartFrame mChartFrame = new ChartFrame("学校人员分布图", mBarChart);
//		mChartFrame.pack();
//		mChartFrame.setVisible(true);
		
		
	}
	public static CategoryDataset createDataSet()
	{
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();		
		mDataset.addValue(2000, "清华大学", "本科生");
		mDataset.addValue(1500, "清华大学", "研究生");
		mDataset.addValue(1000, "清华大学", "博士生");
		mDataset.addValue(900, "清华大学", "讲师");
		mDataset.addValue(800, "清华大学", "副教授");
		mDataset.addValue(300, "清华大学", "教授");
		mDataset.addValue(600, "清华大学", "行政人员");
		mDataset.addValue(400, "清华大学", "管理人员");				 
		return mDataset;
	}
	
	public static void saveAsFile(JFreeChart chart, String outputPath,  
            int width, int height) {  
        FileOutputStream out = null;  
        try {  
            File outFile = new File(outputPath);  
            if (!outFile.getParentFile().exists()) {  
                outFile.getParentFile().mkdirs();  
            }  
            out = new FileOutputStream(outputPath);             
            // 保存为JPEG  
            ChartUtilities.writeChartAsJPEG(out, chart, width, height);  
            out.flush();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (out != null) {  
                try {  
                    out.close();  
                } catch (IOException e) {  
                    // do nothing  
                	e.printStackTrace();
                }  
            }  
        }  
    }  

}