# -- coding: utf-8 --
import cv2
#尺寸调整

def ResetSize(image):
    i, j, k = image.shape
    if(j > i):    #宽度大于高度
        temp = (1500 * i) / j
        ResetSizeImage = cv2.resize(image, (1500, temp), interpolation=cv2.INTER_CUBIC)
    elif(i > j):    #高度大于宽度
        temp = (1000 * j) / i
        ResetSizeImage = cv2.resize(image, (temp, 800), interpolation=cv2.INTER_CUBIC)
    return ResetSizeImage

Image = cv2.imread('3.jpg')
ResetSizeImage = ResetSize(Image)
cv2.imshow('Image', Image)
cv2.imshow('ResetSizeImage', ResetSizeImage)
cv2.waitKey(0)
cv2.destoryAllWindows()