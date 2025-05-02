# pytorch
#### forward()
```py
model = MyModel()
output = model(input_data)
```
不需要显式地调用forward
#### view()
张量通过调用 ```view()``` 进行形状调整。
```py
embeddings = embeddings.view(-1, 3, self.post_fusion_dim)
#第一个维度（-1）表示自动计算，以使得总元素数量保持不变。
#第二个维度为 3，表示每个样本中有 3 个元素。
#第三个维度为self.post_fusion_dim）
```
