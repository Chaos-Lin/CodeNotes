# Basic ML Modeling

## 数据获取
### 数据集
MNIST：手写
ImageNET：图片搜索引擎
AudioSet：YouTube声音切片
Kinetics：视频切片
KITTI：无人驾驶
Amazon Review：用户评论
SQuAD：wikipedia的问题和答案
LibriSpeech：有声读物

### Where to Find Datasets
- Paperswithcodes Datasets: academic datasets with leaderboard学术
- Kaggle Datasets: ML datasets uploaded by data scientists科学家上传的
- Google Dataset search: search datasets in the Web搜索引擎
- Various toolkits datasets: tensorflow库里自带, huggingface文本
- Various conference/company ML competitions竞赛用
- Open Data on AWS: 100+ large-scale raw data原始数据
- Data lakes in your own organization

||Pros |Cons|
|:--:|:--:|:--:|
|Academic datasets|Clean, proper difficulty|Limited choices, too simplified, usually small scale|
|Competition datasets|Closer to real ML applications|Still simplified, and only available for hot topics|
|Raw Data| Great flexibility| Needs a lot of effort to process|
### 生成数据集
use GANs
Data augmentations

## 网页数据抓取
```py
from selenium import webdirver
chrome_options = webdriver.ChromeOptions()
chrome_options.headless = True
chrome = webdriver.Chrome(
    chrome_options=chrome_options)

page = chrome.get(url)
```