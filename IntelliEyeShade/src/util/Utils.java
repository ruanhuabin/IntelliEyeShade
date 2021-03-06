package util;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.orsoncharts.plot.Plot3D;

public class Utils {
	public static CategoryDataset createLineChartDataSet(int[] values, String lineLabel)
	{
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();		
//		mDataset.addValue(2000, "清华大学", "本科生");
//		mDataset.addValue(1500, "清华大学", "研究生");
//		mDataset.addValue(1000, "清华大学", "博士生");
//		mDataset.addValue(900, "清华大学", "讲师");
//		mDataset.addValue(800, "清华大学", "副教授");
//		mDataset.addValue(300, "清华大学", "教授");
//		mDataset.addValue(600, "清华大学", "行政人员");
//		mDataset.addValue(400, "清华大学", "管理人员");
		
		for(int i = 0; i < values.length; i ++)
		{
			dataSet.addValue(values[i] , lineLabel, "" +  (i + 1));
		}
		return dataSet;
	}
	
	public static PieDataset createPieChartDataSet(float[] values, String[] pieLabels)
	{
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for(int i = 0; i < values.length; i ++)
		{
			dataSet.setValue(pieLabels[i], values[i]);
		}
		
		return dataSet;
	}
	
	
	public static void genLineChart(CategoryDataset dataSet, String chartTitle, String xLabel, String yLabel, String outputFilepath)
	{
	
		
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
		
		JFreeChart chart = ChartFactory.createLineChart3D(		
				chartTitle, 	//图表标题
				xLabel, 		//横轴（目录轴）标签
				yLabel, 			//纵轴（数值轴）标签
				dataSet, 		//数据集
				PlotOrientation.VERTICAL,	//图表方向
				false, 			//是否显示图例
				true,			//是否生成提示工具
				false);			//是否生成url连接
		saveAsFile(chart, outputFilepath, 600, 400);		
	}
	
	public static void genPieChart(PieDataset dataSet, String chartTitle, String outputFilePath)
	{
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
		
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataSet, false, true, false);
		PiePlot3D plot = (PiePlot3D)chart.getPlot();
		plot.setForegroundAlpha(0.65f);
		plot.setSimpleLabels(false);
		saveAsFile(chart, outputFilePath, 600,400);
		
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
	
	
	public static void main(String args[])
	{
		int values[] = {9,8,8,7,6,8,7,9};
		
		CategoryDataset ds = createLineChartDataSet(values, "放松度");
		genLineChart(ds, "放松度随时间变化", "时间", "放松度", "D://a.jpg");
		
		
		float fv[] = {0.4f, 0.3f, 0.2f, 0.1f};
		String labels[] = {"16-20岁", "21-25岁", "26-30岁", "31-35岁"};
		PieDataset pds = createPieChartDataSet(fv, labels);
		genPieChart(pds, "年龄分布情况", "D://age.jpg");
		
		
	}
}
