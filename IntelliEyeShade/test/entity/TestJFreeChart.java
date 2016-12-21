package entity;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.UnsupportedEncodingException;
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.LineAndShapeRenderer;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.general.DatasetUtilities;  
  
//JFreeChart Line Chart������ͼ��     
public class TestJFreeChart {  
    /** 
     * ����JFreeChart Line Chart������ͼ�� 
     * @throws UnsupportedEncodingException 
     */  
    public static void main(String[] args) throws UnsupportedEncodingException {  
        // ����1������CategoryDataset����׼�����ݣ�  
        CategoryDataset dataset = createDataset();  
        // ����2������Dataset ����JFreeChart�����Լ�����Ӧ������  
        JFreeChart freeChart = createChart(dataset);  
        // ����3����JFreeChart����������ļ���Servlet�������  
        saveAsFile(freeChart, "d:\\line.jpg", 600, 400);  
    }  
  
    // ����Ϊ�ļ�  
    public static void saveAsFile(JFreeChart chart, String outputPath,  
            int weight, int height) {  
        FileOutputStream out = null;  
        try {  
            File outFile = new File(outputPath);  
            if (!outFile.getParentFile().exists()) {  
                outFile.getParentFile().mkdirs();  
            }  
            out = new FileOutputStream(outputPath);  
            // ����ΪPNG  
            // ChartUtilities.writeChartAsPNG(out, chart, 600, 400);  
            // ����ΪJPEG  
            ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);  
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
                }  
            }  
        }  
    }  
  
    // ����CategoryDataset����JFreeChart����  
    public static JFreeChart createChart(CategoryDataset categoryDataset) {  
        // ����JFreeChart����ChartFactory.createLineChart  
        JFreeChart jfreechart = ChartFactory.createLineChart("��ͬ���Сʱ�������ͼ", // ����  
                "���", // categoryAxisLabel ��category�ᣬ���ᣬX���ǩ��  
                "����", // valueAxisLabel��value�ᣬ���ᣬY��ı�ǩ��  
                categoryDataset, // dataset  
                PlotOrientation.VERTICAL, true, // legend  
                false, // tooltips  
                false); // URLs  
        // ʹ��CategoryPlot���ø��ֲ������������ÿ���ʡ�ԡ�  
        CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();  
        // ����ɫ ͸����  
        plot.setBackgroundAlpha(0.5f);  
        // ǰ��ɫ ͸����  
        plot.setForegroundAlpha(0.5f);  
        // �������� �ο� CategoryPlot��  
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();  
        renderer.setBaseShapesVisible(true); // series �㣨�����ݵ㣩�ɼ�  
        renderer.setBaseLinesVisible(true); // series �㣨�����ݵ㣩�������߿ɼ�  
        renderer.setUseSeriesOffset(true); // ����ƫ����  
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        renderer.setBaseItemLabelsVisible(true);  
        return jfreechart;  
    }  
  
    /** 
     * ����CategoryDataset���� 
     * @throws UnsupportedEncodingException 
     *  
     */  
    public static CategoryDataset createDataset() throws UnsupportedEncodingException {  
    	
    	String str = "רע��";
    	byte[] bs = str.getBytes();
    	String str2 = new String(bs, "GBK");
        String[] rowKeys = {str2};  
        String[] colKeys = {"0:00", "1:00", "2:00", "7:00", "8:00", "9:00",  
                "10:00", "11:00", "12:00", "13:00", "16:00", "20:00", "21:00",  
                "23:00"};  
        double[][] data = {{4, 3, 1, 1, 1, 1, 2, 2, 2, 1, 8, 2, 1, 1},};  
        // ����ʹ���������´���  
        // DefaultCategoryDataset categoryDataset = new  
        // DefaultCategoryDataset();  
        // categoryDataset.addValue(10, "rowKey", "colKey");  
        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);  
    }  
}  