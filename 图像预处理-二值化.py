# -- coding: utf-8 --
import cv2
import numpy as np
#二值化

def ConvertGrayToWhiteBlack(image):
    height, width = image.shape
    w = width
    h = height
    size = (w, h)
    iWhiteBlack = np.zeros(image.shape,np.uint8)
    for i in range(h - 1):
        for j in range(w - 1):
            if(image[i, j] >= 220):
                iWhiteBlack[i, j] = 255
            else:
                iWhiteBlack[i, j] = 0
    return iWhiteBlack

Image = cv2.imread("5.jpg")
GrayImage = cv2.cvtColor(Image, cv2.COLOR_BGR2GRAY)    #灰度化
WhiteBlackImage = ConvertGrayToWhiteBlack(GrayImage)    #二值化
cv2.imshow("Image", Image)
cv2.imshow("grayImage", GrayImage)
cv2.imshow("WhiteBlackImage", WhiteBlackImage)
cv2.waitKey(0)
cv2.destroyAllWindows()
