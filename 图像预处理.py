# -- coding: utf-8 --
import cv2
import numpy as np

def ResetSize(image):    #尺寸调整
    i, j, k = image.shape
    if(j > i):
        temp = (1500 * i) / j
        ResetSizeImage = cv2.resize(image, (1500, temp), interpolation=cv2.INTER_CUBIC)
    elif(i > j):
        temp = (800 * j) / i
        ResetSizeImage = cv2.resize(image, (temp, 800), interpolation=cv2.INTER_CUBIC)
    return ResetSizeImage

def ConvertGrayToWhiteBlack(image):    #二值化
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

def RemoveScatterNoise(image):    #去除离散噪声
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
                if(image[i - 1, j - 1] == 0):
                    n = n + 1
                if (image[i - 1, j] == 0):
                    n = n + 1
                if (image[i - 1, j + 1] == 0):
                    n = n + 1
                if (image[i, j - 1] == 0):
                    n = n + 1
                if (image[i, j + 1] == 0):
                    n = n + 1
                if (image[i + 1, j - 1] == 0):
                    n = n + 1
                if (image[i + 1, j] == 0):
                    n = n + 1
                if (image[i + 1, j + 1] == 0):
                    n = n + 1
            if(n >= 2):
                iRemScat[i, j] = 0
            else:
                iRemScat[i, j] = 255
    return iRemScat

def SlopeAdjust(image):    #倾斜度调整
    iSlope = np.zeros(image.shape, np.uint8)
    height, width = image.shape
    h = height
    w = width
    counts = 0
    leftaver = 0.0
    rightaver = 0.0
    for i in range(h - 1):
        for j in range(w / 2 - 1):
            if(image[i, j] == 0):
                counts += w / 2 - j
                leftaver += i * (w / 2 - j)
    leftaver /= counts
    counts = 0
    for i in range(h - 1):
        for j in range(w / 2, w - 1):
            if(image[i, j] == 0):
                counts += w - j
                rightaver += i * (w - j)
    rightaver /= counts
    slope = (leftaver - rightaver) / (w / 2)
    for i in range(h - 1):
        for j in range(w - 1):
            iSlope[i, j] = 255
    for i in range(h - 1):
        for j in range(w - 1):
            i_src = int(i - (j - w / 2) * slope)
            if (i_src < 0 or i_src >= h):
                iSlope[0, 0] = 255
            else:
                iSlope[i_src, j] = image[i, j]
    return iSlope

def CutImage(image):    #分割字符归一化
    height, width = image.shape
    w = width
    h = height
    root_path = "G:\Users\2\jpg"
    cut_img = []
    left = []
    right = []
    count = -1
    count1 = 0
    upordown = True
    leftorright = True
    for i in range(h - 1):
        count += 1
        sign = False
        if(upordown):
            for j in range(w - 1):
                if(image[i, j] == 0):
                    sign = True
            if(sign):
                up = i
                upordown = False
        if(not upordown):
            for j in range(w - 1):
                if(image[i, j] == 0):
                    sign = True
            if(not sign):
                down = i
                upordown = True
                for m in range(w - 1):
                    sign = False
                    if(leftorright):
                        for n in range(up,down):
                            if(image[n, m] == 0):
                                sign = True
                        if(sign):
                            left = m
                            leftorright = False
                    if(not leftorright):
                        for n in range(up,down):
                            if(image[n, m] == 0):
                                sign = True
                        if(not sign):
                            right = m
                            leftorright = True
                            cut = image[up-1:down+1, left-1:right+1]
                            cut_img.append(cut)
                            cv2.imencode('.jpg', cut_img[count1])[1].tofile('jpg/'+ str(count1)+'.jpg')
                            count1 += 1

Image = cv2.imread("9.jpg")
ResetSizeImage = ResetSize(Image)    #尺寸调整
GrayImage = cv2.cvtColor(ResetSizeImage, cv2.COLOR_BGR2GRAY)    #灰度化
WhiteBlackImage = ConvertGrayToWhiteBlack(GrayImage)    #二值化
CutImage(WhiteBlackImage)    #分割字符归一化
cv2.waitKey(0)
cv2.destroyAllWindows()