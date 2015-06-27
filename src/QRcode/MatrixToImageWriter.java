package QRcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


public final class MatrixToImageWriter {

  private static final int BLACK = 0xFF000000;
  private static final int WHITE = 0xFFFFFFFF;
  private String content = null;
  private ByteArrayOutputStream ops =null;
  public MatrixToImageWriter(String conntent) {
	  try {
          
		     this.content = conntent;
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     //����QR��ά�����
		     Map hints = new HashMap();
		     //���ñ��뷽ʽ
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     //���� ��������,��������,ͼƬ���,ͼƬ�߶�,���ò���
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 430, 430,hints);
//		     File file1 = new File(path,"�ͽ�ֽ.jpg");
//		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		     this.ops = new ByteArrayOutputStream();
		     //��ͼƬд���ֽ���
		     MatrixToImageWriter.writeToStream(bitMatrix, "jpg", ops);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
  }
  public ByteArrayOutputStream getByteArrayOutputStream(){
	  return this.ops;
  }
  
  /**	����һ��ͼƬ
 * @param matrix
 * @return
 */
public static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
      }
    }
    return image;
  }

  
  public static void writeToFile(BitMatrix matrix, String format, File file)
      throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, file)) {
      throw new IOException("Could not write an image of format " + format + " to " + file);
    }
  }
/*static boolean write(RenderedImage im, String formatName, OutputStream output) 
          ʹ��֧�ָ�����ʽ������ ImageWriter ��һ��ͼ��д�� OutputStream�� */
  /**ʹ��֧�ָ�����ʽ������ ImageWriter ��һ��ͼ��д�� OutputStream
 * @param matrix
 * @param format
 * @param stream
 * @throws IOException
 */
public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
      throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, stream)) {
      throw new IOException("Could not write an image of format " + format);
    }
  }
  
	 
}