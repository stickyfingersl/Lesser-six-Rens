import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField input1, input2, input3, result;
    private JButton calculateButton;

    public Calculator() {
        setTitle("小六壬");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顶部显示BCD
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        for (char c : "流连   速喜   赤口".toCharArray()) {
            topPanel.add(new JLabel(String.valueOf(c)));
        }
        add(topPanel, BorderLayout.NORTH);

        // 空白行（使用空面板模拟）
        JPanel blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(0, 20));
        add(blankPanel, BorderLayout.CENTER); // 注意：这里其实并不会真正创建空白行，但可以用来调整间距

        // 中间显示AFE
        JPanel middlePanel = new JPanel(new GridLayout(1, 3));
        for (char c : "大安   空亡   小吉".toCharArray()) {
            middlePanel.add(new JLabel(String.valueOf(c)));
        }
        add(middlePanel, BorderLayout.CENTER); // 实际上，这里可能需要更精细的布局控制

        // 输入框
        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // 10是水平间距
        input1 = new JTextField(5);
        input2 = new JTextField(5);
        input3 = new JTextField(5);
        inputPanel.add(input1);
        inputPanel.add(input2);
        inputPanel.add(input3);


        // 按钮
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        // 结果框
        result = new JTextField(20);
        result.setEditable(false);

        // 组装输入框、按钮和结果框
        JPanel inputPanelAndbuttonAndResultPanel = new JPanel(new BorderLayout(10, 0)); // 10是垂直间距
        inputPanelAndbuttonAndResultPanel.add(inputPanel,BorderLayout.NORTH);
        inputPanelAndbuttonAndResultPanel.add(calculateButton, BorderLayout.CENTER);
        inputPanelAndbuttonAndResultPanel.add(result, BorderLayout.SOUTH);
        add(inputPanelAndbuttonAndResultPanel, BorderLayout.SOUTH);



        setVisible(true);
    }

    private void calculate() {
        try {
            int x = Integer.parseInt(input1.getText());
            int y = Integer.parseInt(input2.getText());
            int z = Integer.parseInt(input3.getText());

            int sum1 = x % 6;
            int sum2 = (x + y) % 6;
            int sum3 = (x + y + z) % 6;

            String[] letters = {"空亡", "大安", "流连", "速喜", "赤口", "小吉"};
            String resultStr = letters[sum1] + " "+letters[sum2] +" "+ letters[sum3];

            result.setText(resultStr);
        } catch (NumberFormatException e) {
            result.setText("Error: Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        // 确保GUI创建在事件调度线程中
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
