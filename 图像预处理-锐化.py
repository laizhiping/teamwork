# -- coding: utf-8 --
import cv2
import numpy as np
#锐化

def Sharp(image):    #锐化
    height, width = image.shape
    w = width
    h = height
    size = (w, h)
    iSharp = np.zeros(image.shape,np.uint8)
    for i in range(h - 1):
        for j in range(w - 1):
            temp = abs(image[i, j] - image[i, j + 1]) + abs(image[i, j] - image[i + 1, j])
            if (temp.any() > 0):
                iSharp[i, j] = 255
            else:
                iSharp[i, j] = image[i, j]
    return iSharp