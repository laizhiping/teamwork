# -- coding: utf-8 --
import cv2
import numpy as np
#去除离散噪声

def RemoveScatterNoise(image):
    height, width = image.shape
    w = width
    h = height
    size = (w, h)
    iRemScat = np.zeros(image.shape, np.uint8)
    for i in range(1, h - 1):
        for j in range(1, w - 1):
            n = 0
            if(image[i, j] == 255):
                iRemScat[i, j] = 255
            else:
                if(image[i - 1, j - 1] == 0):    #左上角
                    n = n + 1
                if (image[i - 1, j] == 0):    #上方
                    n = n + 1
                if (image[i - 1, j + 1] == 0):    #右上角
                    n = n + 1
                if (image[i, j - 1] == 0):    #左方
                    n = n + 1
                if (image[i, j + 1] == 0):    #右方
                    n = n + 1
                if (image[i + 1, j - 1] == 0):    #左下角
                    n = n + 1
                if (image[i + 1, j] == 0):    #下方
                    n = n + 1
                if (image[i + 1, j + 1] == 0):    #右下角
                    n = n + 1
            if(n >= 2):    #周围8个点中有2个以上的黑点则不是噪声
                iRemScat[i, j] = 0
            else:
                iRemScat[i, j] = 255
    return iRemScat