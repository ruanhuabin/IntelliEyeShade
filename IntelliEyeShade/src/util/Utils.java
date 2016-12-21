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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Utils {
	public static CategoryDataset createDataSet(int[] values, String lineLabel)
	{
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();		
//		mDataset.addValue(2000, "�廪��ѧ", "������");
//		mDataset.addValue(1500, "�廪��ѧ", "�о���");
//		mDataset.addValue(1000, "�廪��ѧ", "��ʿ��");
//		mDataset.addValue(900, "�廪��ѧ", "��ʦ");
//		mDataset.addValue(800, "�廪��ѧ", "������");
//		mDataset.addValue(300, "�廪��ѧ", "����");
//		mDataset.addValue(600, "�廪��ѧ", "������Ա");
//		mDataset.addValue(400, "�廪��ѧ", "������Ա");
		
		for(int i = 0; i < values.length; i ++)
		{
			dataSet.addValue(values[i] , lineLabel, "" +  (i + 1));
		}
		return dataSet;
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
            // ����ΪJPEG  
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
	
	public static void genLineChart(CategoryDataset dataSet, String chartTitle, String xLabel, String yLabel, String outputFilepath)
	{
	
		
		//����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		//���ñ�������
		mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		//������������
		mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));
		//����ͼ������
		mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));
		//Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);
		///////////////�����������ñ���λ�ڴ���ͼ������֮ǰ������Ч////////////
		
		JFreeChart chart = ChartFactory.createLineChart3D(		
				chartTitle, 	//ͼ������
				xLabel, 		//���ᣨĿ¼�ᣩ��ǩ
				yLabel, 			//���ᣨ��ֵ�ᣩ��ǩ
				dataSet, 		//���ݼ�
				PlotOrientation.VERTICAL,	//ͼ������
				true, 			//�Ƿ���ʾͼ��
				true,			//�Ƿ�������ʾ����
				false);			//�Ƿ�����url����
		saveAsFile(chart, outputFilepath, 600, 400);		
	}

	
	public static void main(String args[])
	{
		int values[] = {9,8,8,7,6,8,7,9};
		
		CategoryDataset ds = createDataSet(values, "���ɶ�");
		genLineChart(ds, "���ɶ���ʱ��仯", "ʱ��", "���ɶ�", "D://a.jpg");
		
		
		
	}
}