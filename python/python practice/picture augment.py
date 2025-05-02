# import matplotlib.pyplot as plt
import torch
import torchvision
from torch import nn
from d2l import torch as d2l
import os
os.environ["KMP_DUPLICATE_LIB_OK"]  =  "TRUE"


d2l.set_figsize()
img = d2l.Image.open('peach.png')
# d2l.plt.imshow(img)
# d2l.plt.show()

def apply(img, aug, num_rows=2, num_cols=4, scale=2):
    Y = [aug(img) for _ in range(num_rows * num_cols)]
    d2l.show_images(Y, num_rows, num_cols, scale=scale)

# apply(img, torchvision.transforms.RandomHorizontalFlip())
# 左右翻转

# apply(img, torchvision.transforms.RandomVerticalFlip())
# 上下翻转

shape_aug = torchvision.transforms.RandomResizedCrop(
    (200, 200), scale=(0.1, 1), ratio=(0.5, 2))
# apply(img, shape_aug)
# 剪裁

# apply(img, torchvision.transforms.ColorJitter(
#     brightness=0.5, contrast=0, saturation=0, hue=0))
# 改变亮度

# apply(img, torchvision.transforms.ColorJitter(
#     brightness=0, contrast=0, saturation=0, hue=0.5))
# 改变色调

color_aug = torchvision.transforms.ColorJitter(
    brightness=0.5, contrast=0.5, saturation=0.5, hue=0.5)
# apply(img, color_aug)
# 改变颜色

augs = torchvision.transforms.Compose([
    torchvision.transforms.RandomHorizontalFlip(), color_aug, shape_aug])
apply(img, augs)
# 多种方式综合

d2l.plt.show()