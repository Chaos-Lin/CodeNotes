# DL
```py
import os
os.environ["KMP_DUPLICATE_LIB_OK"]  =  "TRUE"
```

#### OSError: Can‘t load tokenizer for ‘bert-base-uncased‘.

需要自行从hugging face上下载这个权重文件，并将其置于代码的根目录下创建一个与**bert-base-uncased**名字一致的文件夹，并将**config.json pytorch_model.bin vocab.text** 放入其中

#### No model name: transformers.modeling_bert

```from transformers.modeling_bert```
改为
```from transformers.models.bert.modeling_bert```


# conda
#### 创建环境
```conda conda create --name <your_env_name> python=3.9```

#### 激活环境
```conda activate <your_env_name>```
如果失败请直接在setting/project/add interpret/conda/exsted

#### Install dependencies from pip
```pip install -r requirements.txt```
#### Install dependencies from conda
```conda install gdal```
```conda install pytorch=1.13.0 torchvision=0.14 pytorch-cuda=11.6 -c pytorch -c nvidia```


# cuda

```py
# 加载模型并将其映射到CPU
model = torch.load('model.pth', map_location=torch.device('cpu'))
```


# IDEA
```html
# Plugin 'org.springframeworkboot:spring-boot-maven-plugin:2.6.13' not found
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<version>2.6.13</version>
</plugin>

# 具体版本号写入并重新编译
```

# Ubuntu 

#### sudo: python: command not found
用```python3```代替```python```

#### Unable to Correct Problems ‘You have Held Broken Packages’
用```aptitude```代替```apt-get```

